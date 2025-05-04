package main.inventory;

import main.item.Item;

public class Slot
{
    private Item storedItem;
    private SlotType slotType;
    private int width;
    private int height;
    private int colNum;
    private int rowNum;

    public Item getStoredItem() {return storedItem;}
    public SlotType getSlotType() {return slotType;}
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public int getColNum() {return colNum;}
    public int getRowNum() {return rowNum;}
    public void setStoredItem(Item storedItem) {this.storedItem = storedItem;}

    public Slot(SlotType slotType, int width, int height, int rowNum, int colNum)
    {
        this.slotType = slotType;
        this.width = width;
        this.height = height;
        this.colNum = colNum;
        this.rowNum = rowNum;
    }

    public Slot(SlotType slotType, int width, int height)
    {
        this(slotType, width, height, -1, -1);
    }
}
