package main.item.armor.shield;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class DiamondShield extends Item
{
    public DiamondShield()
    {
        super(ItemID.DIAMOND_SHIELD);
    }

    public DiamondShield(Position worldPosition)
    {
        super(ItemID.DIAMOND_SHIELD, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Diamond shield");
    }
}
