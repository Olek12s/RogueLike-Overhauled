package main.item.armor.pants;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class IronPants extends Item
{
    public IronPants()
    {
        super(ItemID.IRON_PANTS);
    }

    public IronPants(Position worldPosition)
    {
        super(ItemID.IRON_PANTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Iron pants");
    }
}
