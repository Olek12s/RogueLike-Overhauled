package main.world.map;

import main.utilities.Position;
import main.world.worldGeneration.MapGenerator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Map
{
    private final MapID mapID;
    private MapUpdater mapUpdater;
    private MapRenderer mapRenderer;
    private Chunk[][] chunks;
    private int tilesPerSide;

    public MapID getMapID() {return mapID;}
    public MapUpdater getMapUpdater() {return mapUpdater;}
    public MapRenderer getMapRenderer() {return mapRenderer;}
    public Chunk[][] getChunks() {return chunks;}
    public int getTilesPerSide() {return tilesPerSide;}

    /**
     *
     * @param mapID             - ID of map to be generated
     * @param prefferedMapSize  - requested size of map
     */
    public Map(MapID mapID, PrefferedMapSize prefferedMapSize)
    {
        this.tilesPerSide = PrefferedMapSize.mapSizeToInteger(prefferedMapSize, mapID);
        this.mapID = mapID;
        this.mapRenderer = new MapRenderer(this);
        this.mapUpdater = new MapUpdater(this);

        if (!MapManager.getMaps().containsKey(mapID))
        {
            short[][] tileMapIDValues = MapGenerator.generateMap(tilesPerSide, tilesPerSide, mapID);
            createChunks(tileMapIDValues);
            MapManager.putMap(mapID, this);
        }
    }


    private void createChunks(short[][] tileMapIDValues)
    {
        int chunkSize = Chunk.getChunkSize();
        int chunksPerSide = tilesPerSide / chunkSize;
        chunks = new Chunk[chunksPerSide][chunksPerSide];

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int cx = 0; cx < chunksPerSide; cx++)
        {
            for (int cy = 0; cy < chunksPerSide; cy++)
            {
                final int chunkX = cx;
                final int chunkY = cy;

                executor.submit(() -> {
                    Tile[][] tiles = new Tile[chunkSize][chunkSize];
                    for (int tx = 0; tx < chunkSize; tx++) {
                        for (int ty = 0; ty < chunkSize; ty++) {
                            int worldX = chunkX * chunkSize + tx;
                            int worldY = chunkY * chunkSize + ty;

                            Position raw = new Position(worldX * Tile.getTileSize(), worldY * Tile.getTileSize());
                            Position centered = Position.center(raw, mapID);

                            //TileID id = TileID.fromId(tileMapIDValues[worldX][worldY]);
                            tiles[tx][ty] = new Tile(centered, TileID.DEFAULT);
                        }
                    }
                    chunks[chunkX][chunkY] = new Chunk(tiles);
                });
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
