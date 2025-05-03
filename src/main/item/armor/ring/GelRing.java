package main.item.armor.ring;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class GelRing extends Item
{
    public GelRing()
    {
        super(ItemID.GEL_RING);
    }

    public GelRing(Position worldPosition)
    {
        super(ItemID.GEL_RING, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Gel ring");
    }
}
