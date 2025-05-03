package main.item.armor.helmet;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class IronHelmet extends Item
{
    public IronHelmet()
    {
        super(ItemID.IRON_HELMET);
    }

    public IronHelmet(Position worldPosition)
    {
        super(ItemID.IRON_HELMET, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Iron helmet");
    }
}
