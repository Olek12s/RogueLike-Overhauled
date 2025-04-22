package main.world.map;

import main.utilities.Position;
import main.utilities.sprite.Sprite;
import main.utilities.sprite.SpriteSheet;

import java.awt.image.BufferedImage;

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
    public SpriteSheet getSpriteSheet()
    {
        return TileManager.getSpriteSheetByID(this.ID);
    }
    public Sprite getSprite() {return TileManager.getSpriteSheetByID(this.ID).extractFirst();}
    public BufferedImage getSpriteImage() {return TileManager.getSpriteSheetByID(this.ID).extractFirst().getImage();}
    public boolean isCollidable() {return TileManager.isCollidable(ID);}

    public Tile(Position tileWorldPosition, TileID tileID)
    {
        this.ID = tileID;
        this.tileWorldPosition = tileWorldPosition;
        this.health = TileManager.getHealthByID(ID);
    }
}
