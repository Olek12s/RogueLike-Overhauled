package main.inventory;

import main.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory
{
    private static final int INVENTORY_WIDTH_SLOTS = 9;
    private static final int INVENTORY_HEIGHT_SLOTS = 4;
    private static final int INVENTORY_BELT_SLOTS = 6;

    private List<Item> inventoryItemList;
    private Slot[][] inventorySlots;
    private Slot[] beltSlots;
    /*
    private Slot helmetSlot;
    private Slot chestplateSlot;
    private Slot pantsSlot;
    private Slot bootsSlot;
    private Slot shieldSlot;
    private Slot ring1Slot;
    private Slot ring2Slot;
    private Slot amuletSlot;
    */
    private final Map<SlotType, Slot> equipmentSlots;
    private int currentSlotIdx;

    public List<Item> getInventoryItemList() {return inventoryItemList;}
    public Slot[][] getInventorySlots() {return inventorySlots;}
    public Slot[] getBeltSlots() {return beltSlots;}
    public Slot getEquipmentSlot(SlotType type) {
        return equipmentSlots.get(type);
    }
    /*
    public Slot getHelmetSlot() {return helmetSlot;}
    public Slot getChestplateSlot() {return chestplateSlot;}
    public Slot getPantsSlot() {return pantsSlot;}
    public Slot getBootsSlot() {return bootsSlot;}
    public Slot getShieldSlot() {return shieldSlot;}
    public Slot getRing1Slot() {return ring1Slot;}
    public Slot getRing2Slot() {return ring2Slot;}
    public Slot getAmuletSlot() {return amuletSlot;}
    */

    public int getCurrentSlotIdx() {return currentSlotIdx;}

    public Inventory()
    {
        inventoryItemList = new ArrayList<>();

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
        addEquipSlot(SlotType.helmetSlot);
        addEquipSlot(SlotType.chestplateSlot);
        addEquipSlot(SlotType.pantsSlot);
        addEquipSlot(SlotType.bootsSlot);
        addEquipSlot(SlotType.shieldSlot);
        addEquipSlot(SlotType.ring1Slot);
        addEquipSlot(SlotType.ring2Slot);
        addEquipSlot(SlotType.amuletSlot);
    }

    private void addEquipSlot(SlotType type)
    {
        equipmentSlots.put(type, new Slot(type, SlotType.getWidthMultipler(type), SlotType.getHeightMultipler(type)));
    }
}
