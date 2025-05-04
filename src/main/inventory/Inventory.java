package main.inventory;

import main.item.Item;
import main.utilities.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory
{
    public static final int INVENTORY_WIDTH_SLOTS = 9;
    public static final int INVENTORY_HEIGHT_SLOTS = 5;
    public static final int INVENTORY_BELT_SLOTS = 6;

    private List<Item> mainInventoryItemList;
    private Slot[][] inventorySlots;
    private Slot[] beltSlots;
    private final Map<SlotType, Slot> equipmentSlots;
    private int currentSlotIdx;

    public List<Item> getMainInventoryItemList() {return mainInventoryItemList;}
    public Slot[][] getInventorySlots() {return inventorySlots;}
    public Slot[] getBeltSlots() {return beltSlots;}
    public Slot getEquipmentSlot(SlotType type) {
        return equipmentSlots.get(type);
    }


    public int getCurrentSlotIdx() {return currentSlotIdx;}

    public Inventory()
    {
        mainInventoryItemList = new ArrayList<>();

        this.inventorySlots = new Slot[INVENTORY_WIDTH_SLOTS][INVENTORY_HEIGHT_SLOTS];
        for (int i = 0; i < INVENTORY_WIDTH_SLOTS; i++)
        {
            for (int j = 0; j < INVENTORY_HEIGHT_SLOTS; j++)
            {
                inventorySlots[i][j] = new Slot(SlotType.mainInvSlot, SlotType.getWidthMultipler(SlotType.mainInvSlot), SlotType.getHeightMultipler(SlotType.mainInvSlot), i, j);
            }
        }

        beltSlots = new Slot[INVENTORY_BELT_SLOTS];
        for (int i = 0; i < INVENTORY_BELT_SLOTS; i++)
        {
            beltSlots[i] = new Slot(SlotType.beltSlot, SlotType.getWidthMultipler(SlotType.beltSlot), SlotType.getHeightMultipler(SlotType.beltSlot), i, 0);
        }
        /*
        helmetSlot = new Slot(SlotType.helmetSlot, SlotType.getWidthMultipler(SlotType.helmetSlot), SlotType.getHeightMultipler(SlotType.helmetSlot));
        chestplateSlot = new Slot(SlotType.chestplateSlot, SlotType.getWidthMultipler(SlotType.chestplateSlot), SlotType.getHeightMultipler(SlotType.chestplateSlot));
        pantsSlot = new Slot(SlotType.pantsSlot, SlotType.getWidthMultipler(SlotType.pantsSlot), SlotType.getHeightMultipler(SlotType.pantsSlot));
        bootsSlot = new Slot(SlotType.bootsSlot, SlotType.getWidthMultipler(SlotType.bootsSlot), SlotType.getHeightMultipler(SlotType.bootsSlot));
        shieldSlot = new Slot(SlotType.shieldSlot, SlotType.getWidthMultipler(SlotType.shieldSlot), SlotType.getHeightMultipler(SlotType.shieldSlot));
        ring1Slot = new Slot(SlotType.ring1Slot, SlotType.getWidthMultipler(SlotType.ring1Slot), SlotType.getHeightMultipler(SlotType.ring1Slot));
        ring2Slot = new Slot(SlotType.ring2Slot, SlotType.getWidthMultipler(SlotType.ring2Slot), SlotType.getHeightMultipler(SlotType.ring2Slot));
        amuletSlot = new Slot(SlotType.amuletSlot, SlotType.getWidthMultipler(SlotType.amuletSlot), SlotType.getHeightMultipler(SlotType.amuletSlot));
        */
        equipmentSlots = new HashMap<>();
        addArmorSlot(SlotType.helmetSlot);
        addArmorSlot(SlotType.chestplateSlot);
        addArmorSlot(SlotType.pantsSlot);
        addArmorSlot(SlotType.bootsSlot);
        addArmorSlot(SlotType.shieldSlot);
        addArmorSlot(SlotType.ring1Slot);
        addArmorSlot(SlotType.ring2Slot);
        addArmorSlot(SlotType.amuletSlot);
    }

    private void addArmorSlot(SlotType type)
    {
        equipmentSlots.put(type, new Slot(type, SlotType.getWidthMultipler(type), SlotType.getHeightMultipler(type)));
    }


    public boolean addItem(Item item, int slotX, int slotY)
    {

        if (slotX + item.getSlotWidth() > INVENTORY_WIDTH_SLOTS     // check if item's dimensions fit
                ||  slotY + item.getSlotHeight() > INVENTORY_HEIGHT_SLOTS)
        {
            return false;
        }

        // check if all required slots are not occupied by other items
        for (int i = slotX; i < slotX + item.getSlotWidth(); i++)
        {
            for (int j = slotY; j < slotY + item.getSlotHeight(); j++)
            {
                if (inventorySlots[i][j].getStoredItem() != null)
                {
                    return false;   // if slot is occupied - return null
                }
            }
        }

        // reserve required slots for item
        for (int i = slotX; i < slotX + item.getSlotWidth(); i++)
        {
            for (int j = slotY; j < slotY + item.getSlotHeight(); j++)
            {
                inventorySlots[i][j].setStoredItem(item);
            }
        }
        item.setInventoryPosition(new Position(slotX, slotY));
        mainInventoryItemList.add(item);
        return true;
    }

    public boolean addItem(Item item)
    {
        for (int x = 0; x < INVENTORY_WIDTH_SLOTS; x++)
        {
            for (int y = 0; y < INVENTORY_HEIGHT_SLOTS; y++)
            {
                if (addItem(item, x, y))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Item getItemAtFromMainInventory(int x, int y)
    {
        if (x < 0 || x >= INVENTORY_WIDTH_SLOTS || y < 0 || y >= INVENTORY_HEIGHT_SLOTS) // boundaries
        {
            return null;
        }
        return inventorySlots[x][y].getStoredItem();
    }

    public Item getItemAtFromBelt(int x)
    {
        if (x < 0 || x >= INVENTORY_BELT_SLOTS) // boundaries
        {
            return null;
        }
        return beltSlots[x].getStoredItem();
    }

    public void removeItemFromMainInv(Item item)
    {
        Position itemPosition = item.getInventoryPosition();
        if (itemPosition == null) return;

        for (int i = itemPosition.getX(); i < itemPosition.getX() + item.getSlotWidth(); i++)
        {
            for (int j = itemPosition.getY(); j < itemPosition.getY() + item.getSlotHeight(); j++)
            {
                inventorySlots[i][j].setStoredItem(null);
            }
        }
        item.setInventoryPosition(null);
        mainInventoryItemList.remove(item);
    }

    public void removeItemFromMainInv(int x, int y)
    {
        Item item = getItemAtFromMainInventory(x, y);
        if (item != null)
        {
            removeItemFromMainInv(item);
        }
    }

    public void removeItemFromBelt(int x)
    {
        Item item = beltSlots[x].getStoredItem();
        if (item != null)
        {
            beltSlots[x].setStoredItem(null);
        }
    }

    public void removeItemFromEquipmentSlots(SlotType slot)
    {
        equipmentSlots.get(slot).setStoredItem(null);
    }

    public boolean hasCraftMaterials(Map<Item, Integer> requiredMaterials)
    {
        Map<Item, Integer> counts = new HashMap<>();
        for (Item it : mainInventoryItemList)
        {
            counts.put(it, counts.getOrDefault(it, 0) + 1);
        }

        // checking if for every needed material there's enough in inventory
        for (Map.Entry<Item, Integer> req : requiredMaterials.entrySet())
        {
            Item material = req.getKey();
            int needed = req.getValue();
            int have = counts.getOrDefault(material, 0);
            if (have < needed)
            {
                return false;
            }
        }
        return true;
    }
}
