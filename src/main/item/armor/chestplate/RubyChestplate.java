package main.item.armor.chestplate;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class RubyChestplate extends Item
{
    public RubyChestplate()
    {
        super(ItemID.RUBY_CHESTPLATE);
    }

    public RubyChestplate(Position worldPosition)
    {
        super(ItemID.RUBY_CHESTPLATE, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Ruby chestplate");
    }
}
