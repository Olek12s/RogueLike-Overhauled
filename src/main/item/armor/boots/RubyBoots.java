package main.item.armor.boots;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class RubyBoots extends Item
{
    public RubyBoots()
    {
        super(ItemID.RUBY_BOOTS);
    }

    public RubyBoots(Position worldPosition)
    {
        super(ItemID.RUBY_BOOTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Ruby boots");
    }
}
