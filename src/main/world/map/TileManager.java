package main.world.map;

import main.utilities.sprite.Sprite;
import main.utilities.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TileManager
{
    private static HashMap<TileID, SpriteSheet> tileSpriteSheets;


    public TileManager()
    {
        tileSpriteSheets = new HashMap<>();
        loadTiles();
    }

    private void loadTiles()
    {
        try
        {
            tileSpriteSheets.put(TileID.DEFAULT, new SpriteSheet(ImageIO.read(new File("resources/DefaultTile.png")), 64, 64));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Cannot load tile spritesheet: ", e);
        }
    }


    public static int getHealthByID(TileID ID)
    {
        switch(ID)
        {
            case DEFAULT: return Integer.MAX_VALUE;
            default: return 0;
        }
    }

    public static SpriteSheet getSpriteSheetByID(TileID tileID)
    {
        return tileSpriteSheets.get(tileID);
    }
}
