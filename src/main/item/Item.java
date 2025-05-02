package main.item;

import main.GameController;
import main.utilities.Hitbox;
import main.utilities.Position;
import main.utilities.sprite.Sprite;
import main.world.map.MapManager;

public abstract class Item
{
    private int slotWidth;
    private int slotHeight;
    private Position inventoryPosition;
    private Position worldPosition;
    private ItemID itemID;
    private Hitbox hitbox;
    private boolean isInsideInventory;
    private ItemStatistics statistics;


    public Item(ItemID itemID)  // constructor for items inside the inventories
    {
        this.itemID = itemID;
        this.slotWidth = ItemManager.getSlotWidth(itemID);
        this.slotHeight = ItemManager.getSlotHeight(itemID);
        this.hitbox = ItemManager.getHitbox(itemID);
        setInsideInventory();
    }

    public Item(ItemID itemID, Position worldPosition)  // constructor for items outside of the inventories
    {
        this.itemID = itemID;
        this.slotWidth = ItemManager.getSlotWidth(itemID);
        this.slotHeight = ItemManager.getSlotHeight(itemID);
        this.hitbox = ItemManager.getHitbox(itemID);
        dropOnGround(worldPosition);
    }

    public abstract void setStatistics();

    public int getSlotWidth() {return slotWidth;}
    public int getSlotHeight() {return slotHeight;}
    public void setInventoryPosition(Position inventoryPosition) { this.inventoryPosition = inventoryPosition; }
    public Position getInventoryPosition() {return inventoryPosition;}
    public Sprite getSprite()
    {
        return ItemManager.getInstance().getItemSprite(itemID);
    }
    public Position getWorldPosition() {return worldPosition;}
    public ItemID getItemID() {return itemID;}
    public Hitbox getHitbox() {return hitbox;}
    public boolean isInsideInventory() {return isInsideInventory;}
    public void setSlotWidth(int slotWidth) {this.slotWidth = slotWidth;}
    public void setSlotHeight(int slotHeight) {this.slotHeight = slotHeight;}
    public void setWorldPosition(Position worldPosition) {this.worldPosition = worldPosition;}
    public void setItemID(ItemID itemID) {this.itemID = itemID;}
    public void setHitbox(Hitbox hitbox) {this.hitbox = hitbox;}
    public ItemStatistics getStatistics() {return statistics;}
    public void setStatistics(ItemStatistics statistics) {this.statistics = statistics;}

    private void setInsideInventory(boolean isInsideInventory, Position worldPosition)
    {
        if (isInsideInventory)
        {
            MapManager.getCurrentMap().getChunk(worldPosition).removeItem(this);
            this.hitbox.setWorldPosition(null);
            this.worldPosition = null;
        }
        else
        {
            MapManager.getCurrentMap().getChunk(worldPosition).addItem(this);
            this.hitbox.setWorldPosition(worldPosition);
            this.worldPosition = worldPosition;
        }
        this.isInsideInventory = isInsideInventory;
    }
    public void setInsideInventory()
    {
        setInsideInventory(true, this.worldPosition);
    }

    public void dropOnGround(Position worldPos)
    {
        setInsideInventory(false, worldPos);
    }
}
