package main.item.armor.pants;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class WoodenPants extends Item
{
    public WoodenPants()
    {
        super(ItemID.WOODEN_PANTS);
    }

    public WoodenPants(Position worldPosition)
    {
        super(ItemID.WOODEN_PANTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Wooden pants");
    }
}
