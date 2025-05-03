package main.item.armor.shield;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class IronShield extends Item
{
    public IronShield()
    {
        super(ItemID.IRON_SHIELD);
    }

    public IronShield(Position worldPosition)
    {
        super(ItemID.IRON_SHIELD, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Iron shield");
    }
}
