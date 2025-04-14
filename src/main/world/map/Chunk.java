package main.world.map;

import main.utilities.Position;

public class Chunk
{
    private static int chunkSize = 8;
    private Position chunkWorldPosition;

    public static int getChunkSize() {return chunkSize;}

    private  Tile[][] tiles;

    public Chunk(Tile[][] tiles)
    {
        chunkWorldPosition = tiles[0][0].getTileWorldPosition();
    }
}
