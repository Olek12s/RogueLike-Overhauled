package main.item.armor.boots;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class IronBoots extends Item
{
    public IronBoots()
    {
        super(ItemID.IRON_BOOTS);
    }

    public IronBoots(Position worldPosition)
    {
        super(ItemID.IRON_BOOTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Iron boots");
    }
}
