package main.item.armor.chestplate;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class WoodenChestplate extends Item
{
    public WoodenChestplate()
    {
        super(ItemID.WOODEN_CHESTPLATE);
    }

    public WoodenChestplate(Position worldPosition)
    {
        super(ItemID.WOODEN_CHESTPLATE, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Wooden chestplate");
    }
}