package main.world.map;

import main.utilities.Position;

public class Tile
{
    private static final int tileSize = 64;
    private TileID ID;
    private Position tileWorldPosition;
    private int health;

    public TileID getID() {return ID;}
    public Position getTileWorldPosition() {return tileWorldPosition;}
    public int getHealth() {return health;}
    public static int getTileSize() {return tileSize;}

    public Tile(Position tileWorldPosition, TileID tileID)
    {
        this.ID = tileID;
        this.tileWorldPosition = tileWorldPosition;
        this.health = TileManager.getHealthByID(ID);
    }
}
