package main.world.map;

import main.utilities.Position;

public class Chunk
{
    private static final int chunkSize = 8;
    private Tile[][] tiles;
    private Position chunkWorldPosition;

    public static int getChunkSize() {return chunkSize;}

    public Chunk(Tile[][] tiles)
    {
        chunkWorldPosition = tiles[0][0].getTileWorldPosition();
    }
}
