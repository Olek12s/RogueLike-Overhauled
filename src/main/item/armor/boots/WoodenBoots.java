package main.item.armor.boots;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class WoodenBoots extends Item
{
    public WoodenBoots()
    {
        super(ItemID.WOODEN_BOOTS);
    }

    public WoodenBoots(Position worldPosition)
    {
        super(ItemID.WOODEN_BOOTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Wooden boots");
    }
}
