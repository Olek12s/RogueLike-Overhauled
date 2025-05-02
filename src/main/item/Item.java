package main.item;

import main.utilities.Hitbox;
import main.utilities.Position;
import main.utilities.sprite.Sprite;

public abstract class Item
{
    private int slotWidth;
    private int slotHeight;
    private Position inventoryPosition;
    private Position worldPosition;
    private ItemID itemID;
    private Hitbox hitbox;
    private boolean isInsideInventory;


    public Item(ItemID itemID)  // constructor for items inside the inventories
    {
        this.itemID = itemID;
        this.slotWidth = ItemManager.getSlotWidth(itemID);
        this.slotHeight = ItemManager.getSlotHeight(itemID);
        this.hitbox = ItemManager.getHitbox(itemID);
    }

    public Item(ItemID itemID, Position worldPosition)  // constructor for items outside of the inventories
    {
        this(itemID);
        this.worldPosition = worldPosition;
    }

    public int getSlotWidth() {return slotWidth;}
    public int getSlotHeight() {return slotHeight;}
    public void setInventoryPosition(Position inventoryPosition) { this.inventoryPosition = inventoryPosition; }
    public Position getInventoryPosition() {return inventoryPosition;}
    public Sprite getSprite()
    {
        return ItemManager.getInstance().getItemSprite(itemID);
    }

    private void setInsideInventory(boolean isInsideInventory, Position worldPosition)
    {
        if (isInsideInventory)
        {
             this.hitbox.setWorldPosition(null);
             this.worldPosition = null;
        }
        else
        {
            this.hitbox.setWorldPosition(worldPosition);
            this.worldPosition = worldPosition;
        }

        this.isInsideInventory = isInsideInventory;
    }
    private void setInsideInventory()
    {
        setInsideInventory(true, null);
    }
}
