package main.item.armor.chestplate;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class DiamondChestplate extends Item
{
    public DiamondChestplate()
    {
        super(ItemID.DIAMOND_CHESTPLATE);
    }

    public DiamondChestplate(Position worldPosition)
    {
        super(ItemID.DIAMOND_CHESTPLATE, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Diamond chestplate");
    }
}
