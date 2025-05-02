package main.item.ingredients;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class RedFlower extends Item
{
    public RedFlower()
    {
        super(ItemID.RED_FLOWER);
    }

    public RedFlower(Position worldPosition)
    {
        super(ItemID.RED_FLOWER, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Red flower");
    }
}
