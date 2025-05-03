package main.item.armor.helmet;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class RubyHelmet extends Item
{
    public RubyHelmet()
    {
        super(ItemID.RUBY_HELMET);
    }

    public RubyHelmet(Position worldPosition)
    {
        super(ItemID.RUBY_HELMET, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Ruby helmet");
    }
}
