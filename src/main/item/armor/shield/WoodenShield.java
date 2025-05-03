package main.item.armor.shield;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class WoodenShield extends Item
{
    public WoodenShield()
    {
        super(ItemID.WOODEN_SHIELD);
    }

    public WoodenShield(Position worldPosition)
    {
        super(ItemID.WOODEN_SHIELD, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Wooden shield");
    }
}
