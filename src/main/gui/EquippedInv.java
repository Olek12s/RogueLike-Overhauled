package main.gui;

import main.inventory.Inventory;

import java.awt.*;

public class EquippedInv
{
    public void renderEquippedFrame(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();
        int equippedFrameWidth = slotSize * 5;
        int equippedFrameHeight = slotSize * 10 + slotSize / 4;
        Inventory playerInventory = hud.gc.player.getInventory();

        // positions of equipped frame
        int width = hud.gc.getWidth();
        int height = hud.gc.getHeight();
        int widthSlots = Inventory.INVENTORY_WIDTH_SLOTS;
        int heightSlots = Inventory.INVENTORY_HEIGHT_SLOTS;
        int totalWidth = widthSlots * slotSize;    // width of main inventory
        int totalHeight = heightSlots * slotSize;  // height of main inventory
        int beltY = beltPosition.y - height/2;

        // position of equipped frame
        int equippedFrameX = mainInventoryFramePosition.x + totalWidth;
        int equippedFrameY = mainInventoryFramePosition.y;

        if (equippedPosition == null) equippedPosition = new Position(equippedFrameX, equippedFrameY);
        else
        {
            equippedPosition.x = equippedFrameX;
            equippedPosition.y = equippedFrameY;
        }

        renderFrame(g2d, equippedFrameX, equippedFrameY, equippedFrameWidth, equippedFrameHeight, 3, 3, 1, 0.7f);

        // 1. Helmet (2x2)
        int helmetX = equippedFrameX + (slotSize / 4);
        int helmetY = equippedFrameY + (slotSize / 4);
        if (helmetSlotPosition == null) helmetSlotPosition = new Position(helmetX, helmetY);
        else
        {
            helmetSlotPosition.x = helmetX;
            helmetSlotPosition.y = helmetY;
        }
        renderFrame(g2d, helmetX, helmetY, slotSize * 2, slotSize * 2, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getHelmetSlot().getStoredItem(), helmetX, helmetY, false);


        // 2. Chestplate (2x3)
        int chestX = helmetX;
        int chestY = helmetY + slotSize * 2 + (slotSize / 4);
        renderFrame(g2d, chestX, chestY, slotSize * 2, slotSize * 3, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getChestplateSlot().getStoredItem(), chestX, chestY, false);
        if (chestplateSlotPosition == null) chestplateSlotPosition = new Position(chestX, chestY);
        else
        {
            chestplateSlotPosition.x = chestX;
            chestplateSlotPosition.y = chestY;
        }

        // 3. Pants (2x3)
        int pantsX = chestX;
        int pantsY = chestY + slotSize * 3 + (slotSize / 4);
        renderFrame(g2d, pantsX, pantsY, slotSize * 2, slotSize * 3, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getPantsSlot().getStoredItem(), pantsX, pantsY, false);
        if (pantsSlotPosition == null) pantsSlotPosition = new Position(pantsX, pantsY);
        else
        {
            pantsSlotPosition.x = pantsX;
            pantsSlotPosition.y = pantsY;
        }

        // 4. Boots (2x1)
        int bootsX = pantsX;
        int bootsY = pantsY + slotSize * 3 + (slotSize / 4);
        renderFrame(g2d, bootsX, bootsY, slotSize * 2, slotSize, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getBootsSlot().getStoredItem(), bootsX, bootsY, false);
        if (bootsSlotPosition == null) bootsSlotPosition = new Position(bootsX, bootsY);
        else
        {
            bootsSlotPosition.x = bootsX;
            bootsSlotPosition.y = bootsY;
        }

        // 5. Ring1 (1x1)
        int ring1X = equippedFrameX + slotSize * 2 + (slotSize / 2);
        int ring1Y = equippedFrameY + (equippedFrameHeight / 2) - (slotSize * 2);
        renderFrame(g2d, ring1X, ring1Y, slotSize, slotSize, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getRing1Slot().getStoredItem(), ring1X, ring1Y, false);
        if (ring1SlotPosition == null) ring1SlotPosition = new Position(ring1X, ring1Y);
        else
        {
            ring1SlotPosition.x = ring1X;
            ring1SlotPosition.y = ring1Y;
        }

        // 6. Ring2 (1x1)
        int ring2X = ring1X + slotSize + (slotSize / 4);
        int ring2Y = ring1Y;
        renderFrame(g2d, ring2X, ring2Y, slotSize, slotSize, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getRing2Slot().getStoredItem(), ring2X, ring2Y, false);
        if (ring2SlotPosition == null) ring2SlotPosition = new Position(ring2X, ring2Y);
        else
        {
            ring2SlotPosition.x = ring2X;
            ring2SlotPosition.y = ring2Y;
        }

        // 7. Amulet (1x1)
        int amuletX = ring1X + (slotSize / 2) + ((slotSize / 4) / 2);
        int amuletY = ring1Y - slotSize - (slotSize / 4);
        renderFrame(g2d, amuletX, amuletY, slotSize, slotSize, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getAmuletSlot().getStoredItem(), amuletX, amuletY, false);
        if (amuletSlotPosition == null) amuletSlotPosition = new Position(amuletX, amuletY);
        else
        {
            amuletSlotPosition.x = amuletX;
            amuletSlotPosition.y = amuletY;
        }

        // 8. Shield (2x2)
        int shieldX = ring1X + ((slotSize / 4) / 2);
        int shieldY = ring2Y + slotSize + (slotSize / 4);
        renderFrame(g2d, shieldX, shieldY, slotSize * 2, slotSize * 2, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getShieldSlot().getStoredItem(), shieldX, shieldY, false);
        if (shieldSlotPosition == null) shieldSlotPosition = new Position(shieldX, shieldY);
        else
        {
            shieldSlotPosition.x = shieldX;
            shieldSlotPosition.y = shieldY;
        }

        g2d.dispose();
    }
}
