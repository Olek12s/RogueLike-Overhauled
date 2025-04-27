package main.inventory;

public enum SlotType
{
    mainInvSlot,
    beltSlot,
    helmetSlot,
    chestplateSlot,
    pantsSlot,
    bootsSlot,
    shieldSlot,
    ring1Slot,
    ring2Slot,
    amuletSlot;

    public static int getWidthMultipler(SlotType ID)
    {
        switch (ID)
        {
            case mainInvSlot: return 1;
            case beltSlot: return 1;
            case helmetSlot: return 2;
            case chestplateSlot: return 2;
            case pantsSlot: return 2;
            case bootsSlot: return 2;
            case shieldSlot: return 2;
            case ring1Slot: return 1;
            case ring2Slot: return 1;
            case amuletSlot: return 1;
            default: return 1;
        }
    }

    public static int getHeightMultipler(SlotType ID)
    {
        switch (ID)
        {
            case mainInvSlot: return 1;
            case beltSlot: return 1;
            case helmetSlot: return 2;
            case chestplateSlot: return 3;
            case pantsSlot: return 3;
            case bootsSlot: return 1;
            case shieldSlot: return 2;
            case ring1Slot: return 1;
            case ring2Slot: return 1;
            case amuletSlot: return 1;
            default: return 1;
        }
    }
}
