package main.item.armor.helmet;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class WoodenHelmet extends Item
{
    public WoodenHelmet()
    {
        super(ItemID.WOODEN_HELMET);
    }

    public WoodenHelmet(Position worldPosition)
    {
        super(ItemID.WOODEN_HELMET, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Wooden helmet");
    }
}
