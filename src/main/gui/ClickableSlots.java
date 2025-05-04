package main.gui;

import main.inventory.Slot;
import main.utilities.Position;

public interface ClickableSlots
{
    Slot getSlotAt(Position pos);
}
