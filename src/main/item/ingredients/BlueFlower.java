package main.item.ingredients;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class BlueFlower extends Item
{
    public BlueFlower()
    {
        super(ItemID.BLUE_FLOWER);
    }

    public BlueFlower(Position worldPosition)
    {
        super(ItemID.BLUE_FLOWER, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Blue flower");
    }
}
