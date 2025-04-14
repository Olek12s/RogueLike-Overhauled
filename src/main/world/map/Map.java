package main.world.map;

import main.GameController;
import main.utilities.Position;
import main.world.worldGeneration.MapGenerator;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
        int threads = Math.min(1, Thread.activeCount());
        Executor executor = Executors.newFixedThreadPool(threads);
        int threadChunk = size / threads / Chunk.getChunkSize();

        for (int x = 0; x < threadChunk; x++)
        {
            for (int y = 0; y < threadChunk; y++)
            {
                int chunkSize = Chunk.getChunkSize();
                Tile[][] tiles = new Tile[chunkSize][chunkSize];

                for (int tileX = 0; tileX < Chunk.getChunkSize(); tileX++)
                {
                    for (int tileY = 0; tileY < Chunk.getChunkSize(); tileY++)
                    {
                        tiles[tileX][tileY] = new Tile(new Position(), tileMapIDValues[tileX][tileY]);
                    }
                }
                chunks[x][y] = new Chunk(tiles);
            }
        }
    }
}
