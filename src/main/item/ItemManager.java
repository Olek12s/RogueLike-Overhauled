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
            // flowers
            sprites.put(ItemID.RED_FLOWER,    new Sprite(ImageIO.read(new File("resources/txt/item/ingredients/RedFlower.png"))));
            sprites.put(ItemID.BLUE_FLOWER,   new Sprite(ImageIO.read(new File("resources/txt/item/ingredients/BlueFlower.png"))));
            sprites.put(ItemID.YELLOW_FLOWER, new Sprite(ImageIO.read(new File("resources/txt/item/ingredients/YellowFlower.png"))));

            // boots
            sprites.put(ItemID.WOODEN_BOOTS,  new Sprite(ImageIO.read(new File("resources/txt/item/armor/boots/WoodenBoots.png"))));
            sprites.put(ItemID.IRON_BOOTS,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/boots/IronBoots.png"))));
            sprites.put(ItemID.DIAMOND_BOOTS, new Sprite(ImageIO.read(new File("resources/txt/item/armor/boots/DiamondBoots.png"))));
            sprites.put(ItemID.RUBY_BOOTS,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/boots/RubyBoots.png"))));

            // chestplates
            sprites.put(ItemID.WOODEN_CHESTPLATE,  new Sprite(ImageIO.read(new File("resources/txt/item/armor/chestplate/WoodenChestplate.png"))));
            sprites.put(ItemID.IRON_CHESTPLATE,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/chestplate/IronChestplate.png"))));
            sprites.put(ItemID.DIAMOND_CHESTPLATE, new Sprite(ImageIO.read(new File("resources/txt/item/armor/chestplate/DiamondChestplate.png"))));
            sprites.put(ItemID.RUBY_CHESTPLATE,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/chestplate/RubyChestplate.png"))));

            // pants
            sprites.put(ItemID.WOODEN_PANTS,  new Sprite(ImageIO.read(new File("resources/txt/item/armor/pants/WoodenPants.png"))));
            sprites.put(ItemID.IRON_PANTS,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/pants/IronPants.png"))));
            sprites.put(ItemID.DIAMOND_PANTS, new Sprite(ImageIO.read(new File("resources/txt/item/armor/pants/DiamondPants.png"))));
            sprites.put(ItemID.RUBY_PANTS,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/pants/RubyPants.png"))));

            // helmets
            sprites.put(ItemID.WOODEN_HELMET,  new Sprite(ImageIO.read(new File("resources/txt/item/armor/helmet/WoodenHelmet.png"))));
            sprites.put(ItemID.IRON_HELMET,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/helmet/IronHelmet.png"))));
            sprites.put(ItemID.DIAMOND_HELMET, new Sprite(ImageIO.read(new File("resources/txt/item/armor/helmet/DiamondHelmet.png"))));
            sprites.put(ItemID.RUBY_HELMET,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/helmet/RubyHelmet.png"))));

            // shields
            sprites.put(ItemID.WOODEN_SHIELD,  new Sprite(ImageIO.read(new File("resources/txt/item/armor/shield/WoodenShield.png"))));
            sprites.put(ItemID.IRON_SHIELD,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/shield/IronShield.png"))));
            sprites.put(ItemID.DIAMOND_SHIELD, new Sprite(ImageIO.read(new File("resources/txt/item/armor/shield/DiamondShield.png"))));
            sprites.put(ItemID.RUBY_SHIELD,    new Sprite(ImageIO.read(new File("resources/txt/item/armor/shield/RubyShield.png"))));

            // rings
            sprites.put(ItemID.GEL_RING,   new Sprite(ImageIO.read(new File("resources/txt/item/armor/ring/GelRing.png"))));

            // amulets
            sprites.put(ItemID.GEL_AMULET, new Sprite(ImageIO.read(new File("resources/txt/item/armor/amulet/GelAmulet.png"))));
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
            case RED_FLOWER           -> 1;
            case BLUE_FLOWER          -> 1;
            case YELLOW_FLOWER        -> 1;

            case WOODEN_BOOTS         -> 2;
            case IRON_BOOTS           -> 2;
            case DIAMOND_BOOTS        -> 2;
            case RUBY_BOOTS           -> 2;

            case WOODEN_CHESTPLATE    -> 2;
            case IRON_CHESTPLATE      -> 2;
            case DIAMOND_CHESTPLATE   -> 2;
            case RUBY_CHESTPLATE      -> 2;

            case WOODEN_PANTS         -> 2;
            case IRON_PANTS           -> 2;
            case DIAMOND_PANTS        -> 2;
            case RUBY_PANTS           -> 2;

            case WOODEN_HELMET        -> 2;
            case IRON_HELMET          -> 2;
            case DIAMOND_HELMET       -> 2;
            case RUBY_HELMET          -> 2;

            case WOODEN_SHIELD        -> 2;
            case IRON_SHIELD          -> 2;
            case DIAMOND_SHIELD       -> 2;
            case RUBY_SHIELD          -> 2;

            case GEL_RING             -> 1;
            case GEL_AMULET           -> 1;

            default -> throw new IllegalArgumentException("Unknown ItemID: " + itemID);
        };
    }

    public static int getSlotHeight(ItemID itemID)
    {
        return switch (itemID)
        {
            case RED_FLOWER           -> 1;
            case BLUE_FLOWER          -> 1;
            case YELLOW_FLOWER        -> 1;

            case WOODEN_BOOTS         -> 1;
            case IRON_BOOTS           -> 1;
            case DIAMOND_BOOTS        -> 1;
            case RUBY_BOOTS           -> 1;

            case WOODEN_CHESTPLATE    -> 3;
            case IRON_CHESTPLATE      -> 3;
            case DIAMOND_CHESTPLATE   -> 3;
            case RUBY_CHESTPLATE      -> 3;

            case WOODEN_PANTS         -> 3;
            case IRON_PANTS           -> 3;
            case DIAMOND_PANTS        -> 3;
            case RUBY_PANTS           -> 3;

            case WOODEN_HELMET        -> 2;
            case IRON_HELMET          -> 2;
            case DIAMOND_HELMET       -> 2;
            case RUBY_HELMET          -> 2;

            case WOODEN_SHIELD        -> 2;
            case IRON_SHIELD          -> 2;
            case DIAMOND_SHIELD       -> 2;
            case RUBY_SHIELD          -> 2;

            case GEL_RING             -> 1;
            case GEL_AMULET           -> 1;

            default -> throw new IllegalArgumentException("Unknown ItemID: " + itemID);
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
