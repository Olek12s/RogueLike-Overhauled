package main.item.armor.boots;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class DiamondBoots extends Item
{
    public DiamondBoots()
    {
        super(ItemID.DIAMOND_BOOTS);
    }

    public DiamondBoots(Position worldPosition)
    {
        super(ItemID.DIAMOND_BOOTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Diamond boots");
    }
}
