package main.item.armor.pants;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class RubyPants extends Item
{
    public RubyPants()
    {
        super(ItemID.RUBY_PANTS);
    }

    public RubyPants(Position worldPosition)
    {
        super(ItemID.RUBY_PANTS, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Ruby pants");
    }
}
