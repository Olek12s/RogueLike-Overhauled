package main.item.armor.pants;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class DiamondPants extends Item
{
    public DiamondPants()
    {
        super(ItemID.DIAMOND_PANTS);
    }

    public DiamondPants(Position worldPosition)
    {
        super(ItemID.DIAMOND_PANTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Diamond pants");
    }
}
