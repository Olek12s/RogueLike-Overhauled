package main.world.map;

import main.utilities.Position;
import main.world.tile.Tile;
import main.world.tile.TileID;
import main.world.worldGeneration.MapGenerator;

import java.util.ArrayList;
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

    public Chunk getChunk(int worldX, int worldY)
    {
        int chunkPixelSize = Chunk.getChunkSize() * Tile.getTileSize();

        // aligning to the middle of the map
        int halfMapWidthInPixels = (chunks.length / 2) * chunkPixelSize;
        int halfMapHeightInPixels = (chunks[0].length / 2) * chunkPixelSize;
        int adjustedX = worldX + halfMapWidthInPixels;
        int adjustedY = worldY + halfMapHeightInPixels;

        int chunkX = adjustedX / chunkPixelSize;
        int chunkY = adjustedY / chunkPixelSize;

        if (adjustedX < 0) chunkX--;
        if (adjustedY < 0) chunkY--;

        if (chunkX < 0 || chunkX >= chunks.length || chunkY < 0 || chunkY >= chunks[0].length)
        {

            throw new IndexOutOfBoundsException("Position out of map: (" + worldX + ", " + worldY + ")");
        }
        return chunks[chunkX][chunkY];
    }
    public Chunk getChunk(Position worldPosition) {return getChunk(worldPosition.getX(), worldPosition.getY());}
    public Chunk getChunkByIndex(int chunkX, int chunkY) {return chunks[chunkX][chunkY];}

    public ArrayList<Chunk> getChunkNeighborsNotDiagonals(Chunk sourceChunk)
    {
        ArrayList<Chunk> resultChunks = new ArrayList<>();
        int sourceChunkXIndex = sourceChunk.getxIndex();
        int sourceChunkYIndex = sourceChunk.getyIndex();

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if ((i == 0 && j == 0) || (i != 0 && j != 0)) continue; // ignore source chunks and diagonlas

                int neighborX = sourceChunkXIndex + i;
                int neighborY = sourceChunkYIndex + j;

                if (neighborX >= 0 && neighborY >= 0 && neighborX < chunks.length && neighborY < chunks[0].length)
                {
                    Chunk neighbor = chunks[neighborX][neighborY];
                    if (neighbor != null)
                    {
                        resultChunks.add(neighbor);
                    }
                }

            }
        }
        return resultChunks;
    }

    /**
     * Returns a list of all neighboring chunks for the given source chunk,
     * including diagonal neighbors. The source chunk itself is excluded.
     *
     * @param sourceChunk The source chunk for which to retrieve all neighbors.
     * @return An ArrayList containing all neighboring chunks, including those located diagonally.
     */
    public ArrayList<Chunk> getChunkNeighborsDiagonals(Chunk sourceChunk)
    {
        ArrayList<Chunk> resultChunks = new ArrayList<>();
        int sourceChunkXIndex = sourceChunk.getxIndex();
        int sourceChunkYIndex = sourceChunk.getyIndex();

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i == 0 && j == 0) continue; // Ignore the source chunk

                int neighborX = sourceChunkXIndex + i;
                int neighborY = sourceChunkYIndex + j;

                // Check if the neighbor is within map bounds
                if (neighborX >= 0 && neighborY >= 0 && neighborX < chunks.length && neighborY < chunks[0].length)
                {
                    Chunk neighbor = chunks[neighborX][neighborY];
                    if (neighbor != null)
                    {
                        resultChunks.add(neighbor);
                    }
                }
            }
        }
        return resultChunks;
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

                            Position raw = new Position(worldX * Tile.getTileSize() + Tile.getTileSize()/2, worldY * Tile.getTileSize() + Tile.getTileSize()/2);
                            Position centered = Position.center(raw, mapID);

                            TileID id = TileID.fromId(tileMapIDValues[worldX][worldY]);
                            //tiles[tx][ty] = new Tile(centered, TileID.DEFAULT);
                            tiles[tx][ty] = new Tile(centered, id);
                        }
                    }
                    chunks[chunkX][chunkY] = new Chunk(tiles, chunkX, chunkY);
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

    public Tile getTile(int worldX, int worldY)
    {
        int tileSize = Tile.getTileSize();
        int chunkSize = Chunk.getChunkSize();
        int chunkPixelSize = chunkSize * tileSize;

        int halfMapPxX = (chunks.length  / 2) * chunkPixelSize;
        int halfMapPxY = (chunks[0].length / 2) * chunkPixelSize;
        int adjX = worldX + halfMapPxX;
        int adjY = worldY + halfMapPxY;

        int chunkX = Math.floorDiv(adjX, chunkPixelSize);
        int chunkY = Math.floorDiv(adjY, chunkPixelSize);

        if (chunkX < 0 || chunkX >= chunks.length || chunkY < 0 || chunkY >= chunks[0].length)
        {
            throw new IndexOutOfBoundsException(
                    "Position out of map: (" + worldX + ", " + worldY + ")");
        }

        int pxInChunkX = Math.floorMod(adjX, chunkPixelSize);
        int pxInChunkY = Math.floorMod(adjY, chunkPixelSize);
        int tileX = Math.floorDiv(pxInChunkX, tileSize);
        int tileY = Math.floorDiv(pxInChunkY, tileSize);

        return chunks[chunkX][chunkY].getTiles()[tileX][tileY];
    }
    public Tile getTile(Position pos)
    {
        return getTile(pos.getX(), pos.getY());
    }
}
