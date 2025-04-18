package main.world.map;

import main.GameController;
import main.utilities.Position;
import main.world.worldGeneration.MapGenerator;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Map
{
    private MapUpdater mapUpdater;
    private Chunk[][] chunks;

    /**
     *
     * @param mapID             - ID of map to be generated
     * @param prefferedMapSize  - requested size of map
     */
    public Map(MapID mapID, PrefferedMapSize prefferedMapSize)
    {
        if (!MapManager.getMaps().containsKey(mapID))
        {
            int mapSize = PrefferedMapSize.mapSizeToInteger(prefferedMapSize, mapID);
            short[][] tileMapIDValues = MapGenerator.generateMap(mapSize, mapSize, mapID);
            createChunks(tileMapIDValues, PrefferedMapSize.mapSizeToInteger(prefferedMapSize, mapID));
            MapManager.putMap(mapID, this);
        }
    }

    /**
     *
     * @param tileMapIDValues   - IDs of tiles
     * @param size              - number of tiles per side
     */
    private void createChunks(short[][] tileMapIDValues, int size)
    {
        int chunkSize = Chunk.getChunkSize();
        int chunksPerSide = size / chunkSize;
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
                            Position pos = new Position(worldX * Tile.getTileSize(), worldY * Tile.getTileSize());
                            //TileID id = TileID.fromId(tileMapIDValues[worldX][worldY]);
                            if (tx == 0 && ty == 0) tiles[tx][ty] = new Tile(pos, TileID.DEFAULT);
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
