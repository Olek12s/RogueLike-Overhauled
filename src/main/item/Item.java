package main.item;

import main.utilities.Position;
import main.utilities.sprite.Sprite;

public abstract class Item
{
    private int slotWidth;
    private int slotHeight;
    private Position inventoryPosition;

    public int getSlotWidth() {return slotWidth;}
    public int getSlotHeight() {return slotHeight;}
    public void setInventoryPosition(Position inventoryPosition) { this.inventoryPosition = inventoryPosition; }
    public Position getInventoryPosition() {return inventoryPosition;}
    public Sprite getSprite()
    {

    }
}
