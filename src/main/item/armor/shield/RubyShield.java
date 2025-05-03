package main.item.armor.shield;

import main.item.Item;
import main.item.ItemID;
import main.utilities.Position;

public class RubyShield extends Item
{
    public RubyShield()
    {
        super(ItemID.RUBY_SHIELD);
    }

    public RubyShield(Position worldPosition)
    {
        super(ItemID.RUBY_SHIELD, worldPosition);
    }

    @Override
    public void setStatistics()
    {
        this.getStatistics().setItemName("Ruby shield");
    }
}
