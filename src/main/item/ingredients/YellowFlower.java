package main.item.ingredients;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class YellowFlower extends Item
{
    public YellowFlower()
    {
        super(ItemID.YELLOW_FLOWER);
    }

    public YellowFlower(Position worldPosition)
    {
        super(ItemID.YELLOW_FLOWER, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Yellow flower");
    }
}
