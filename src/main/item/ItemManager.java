package main.item;

import main.crafting.CraftingManager;
import main.utilities.sprite.Sprite;

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
        }
        return instance;
    }


    public Sprite getItemSprite(ItemID itemID)
    {
        return sprites.get(itemID);
    }
}
