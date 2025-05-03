package main.item.armor.chestplate;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class IronChestplate extends Item
{
    public IronChestplate()
    {
        super(ItemID.IRON_CHESTPLATE);
    }

    public IronChestplate(Position worldPosition)
    {
        super(ItemID.IRON_CHESTPLATE, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Iron chestplate");
    }
}
