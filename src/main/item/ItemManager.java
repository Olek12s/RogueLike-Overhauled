package main.item;

import main.crafting.CraftingManager;
import main.entity.EntityID;
import main.utilities.Hitbox;
import main.utilities.sprite.Sprite;
import main.utilities.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ItemManager
{
    private static ItemManager instance;
    private static Map<ItemID, Sprite> sprites;

    private ItemManager()
    {
        sprites = new HashMap<>();
    }

    public static ItemManager getInstance()
    {
        if (instance == null)
        {
            instance = new ItemManager();
            loadTextures();
        }
        return instance;
    }


    public static Sprite getItemSprite(ItemID itemID)
    {
        return sprites.get(itemID);
    }

    private static void loadTextures()
    {
        sprites = new HashMap<>();

        try
        {
            sprites.put(ItemID.RED_FLOWER, new Sprite(ImageIO.read(new File("resources/txt/item/ingredients/RedFlower.png"))));
            sprites.put(ItemID.BLUE_FLOWER, new Sprite(ImageIO.read(new File("resources/txt/item/ingredients/BlueFlower.png"))));
            sprites.put(ItemID.YELLOW_FLOWER, new Sprite(ImageIO.read(new File("resources/txt/item/ingredients/YellowFlower.png"))));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static int getSlotWidth(ItemID itemID)
    {
        return switch (itemID)
        {
            case RED_FLOWER    -> 1;
            case BLUE_FLOWER   -> 1;
            case YELLOW_FLOWER -> 1;
            default -> throw new IllegalArgumentException("Unknown ItemID ItemID: " + itemID);
        };
    }

    public static int getSlotHeight(ItemID itemID)
    {
        return switch (itemID)
        {
            case RED_FLOWER    -> 1;
            case BLUE_FLOWER   -> 1;
            case YELLOW_FLOWER -> 1;
            default -> throw new IllegalArgumentException("Unknown ItemID ItemID: " + itemID);
        };
    }

    public static Hitbox getHitbox(ItemID itemID)
    {
        Sprite sprite = getItemSprite(itemID);
        if (sprite == null)
        {
            throw new IllegalArgumentException("Unknown ItemID: " + itemID);
        }

        int width  = sprite.getImage().getWidth();
        int height = sprite.getImage().getHeight();
        return new Hitbox(null, width, height);
    }
}
