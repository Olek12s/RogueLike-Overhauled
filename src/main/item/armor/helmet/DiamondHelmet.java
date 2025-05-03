package main.item.armor.helmet;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class DiamondHelmet extends Item
{
    public DiamondHelmet()
    {
        super(ItemID.DIAMOND_HELMET);
    }

    public DiamondHelmet(Position worldPosition)
    {
        super(ItemID.DIAMOND_HELMET, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Diamond helmet");
    }
}
