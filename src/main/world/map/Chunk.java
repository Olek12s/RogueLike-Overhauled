package main.world.map;

import main.utilities.Position;

public class Chunk
{
    private static final int chunkSize = 8;
    private Tile[][] tiles;
    private Position chunkWorldPosition;

    public Tile[][] getTiles() {return tiles;}
    public Position getChunkWorldPosition() {return chunkWorldPosition;}

    public static int getChunkSize() {return chunkSize;}

    public Chunk(Tile[][] tiles)
    {
        this.tiles = tiles;
        chunkWorldPosition = tiles[0][0].getTileWorldPosition();
    }
}
