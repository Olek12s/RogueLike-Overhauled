package main.item.armor.amulet;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class GelAmulet extends Item
{
    public GelAmulet()
    {
        super(ItemID.GEL_AMULET);
    }

    public GelAmulet(Position worldPosition)
    {
        super(ItemID.GEL_AMULET, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Gel amulet");
    }
}
