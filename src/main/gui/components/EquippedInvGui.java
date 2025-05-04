package main.gui.components;

import main.GameController;
import main.gui.ClickableSlots;
import main.gui.Gui;
import main.inventory.Inventory;
import main.inventory.Slot;
import main.inventory.SlotType;
import main.utilities.Position;

import java.awt.*;

import static main.gui.GuiRenderer.renderFrame;
import static main.gui.GuiRenderer.renderInventoryItem;

public class EquippedInvGui implements ClickableSlots
{
    private Position helmetSlotPosition;
    private Position chestplateSlotPosition;
    private Position pantsSlotPosition;
    private Position bootsSlotPosition;
    private Position shieldSlotPosition;
    private Position ring1SlotPosition;
    private Position ring2SlotPosition;
    private Position amuletSlotPosition;

    public void renderEquippedFrame(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();
        int equippedFrameWidth = Gui.getSlotSize() * 5;
        int equippedFrameHeight = Gui.getSlotSize() * 10 + Gui.getSlotSize() / 4;
        Inventory playerInventory = GameController.getPlayer().getInventory();

        // positions of equipped frame
        int width = GameController.getInstance().getWidth();
        int height = GameController.getInstance().getHeight();
        int widthSlots = Inventory.INVENTORY_WIDTH_SLOTS;
        int heightSlots = Inventory.INVENTORY_HEIGHT_SLOTS;
        int totalWidth = widthSlots * Gui.getSlotSize();    // width of this Gui element
        int totalHeight = heightSlots * Gui.getSlotSize();  // height of this Gui element

        // position of equipped frame
        int equippedFrameX = ((width  - totalWidth ) / 2) + totalWidth;
        int equippedFrameY = (height - totalHeight) / 4;
        //int equippedFrameX = mainInventoryFramePosition.x + totalWidth;
        //int equippedFrameY = mainInventoryFramePosition.y;

        /*
        if (equippedPosition == null) equippedPosition = new Position(equippedFrameX, equippedFrameY);
        else
        {
            equippedPosition.x = equippedFrameX;
            equippedPosition.y = equippedFrameY;
        }
        */

        renderFrame(g2d, equippedFrameX, equippedFrameY, equippedFrameWidth, equippedFrameHeight, 3, 3, 1, 0.7f);

        // 1. Helmet (2x2)
        int helmetX = equippedFrameX + (Gui.getSlotSize() / 4);
        int helmetY = equippedFrameY + (Gui.getSlotSize() / 4);
        if (helmetSlotPosition == null) helmetSlotPosition = new Position(helmetX, helmetY);
        else
        {
            helmetSlotPosition.setX(helmetX);
            helmetSlotPosition.setY(helmetY);
        }
        renderFrame(g2d, helmetX, helmetY, Gui.getSlotSize() * 2, Gui.getSlotSize() * 2, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.helmetSlot).getStoredItem(), helmetX, helmetY, false);


        // 2. Chestplate (2x3)
        int chestX = helmetX;
        int chestY = helmetY + Gui.getSlotSize() * 2 + (Gui.getSlotSize() / 4);
        renderFrame(g2d, chestX, chestY, Gui.getSlotSize() * 2, Gui.getSlotSize() * 3, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.chestplateSlot).getStoredItem(), chestX, chestY, false);
        if (chestplateSlotPosition == null) chestplateSlotPosition = new Position(chestX, chestY);
        else
        {
            chestplateSlotPosition.setX(chestX);
            chestplateSlotPosition.setY(chestY);
        }

        // 3. Pants (2x3)
        int pantsX = chestX;
        int pantsY = chestY + Gui.getSlotSize() * 3 + (Gui.getSlotSize() / 4);
        renderFrame(g2d, pantsX, pantsY, Gui.getSlotSize() * 2, Gui.getSlotSize() * 3, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.pantsSlot).getStoredItem(), pantsX, pantsY, false);
        if (pantsSlotPosition == null) pantsSlotPosition = new Position(pantsX, pantsY);
        else
        {
            pantsSlotPosition.setX(pantsX);
            pantsSlotPosition.setY(pantsY);
        }

        // 4. Boots (2x1)
        int bootsX = pantsX;
        int bootsY = pantsY + Gui.getSlotSize() * 3 + (Gui.getSlotSize() / 4);
        renderFrame(g2d, bootsX, bootsY, Gui.getSlotSize() * 2, Gui.getSlotSize(), 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.bootsSlot).getStoredItem(), bootsX, bootsY, false);
        if (bootsSlotPosition == null) bootsSlotPosition = new Position(bootsX, bootsY);
        else
        {
            bootsSlotPosition.setX(bootsX);
            bootsSlotPosition.setY(bootsY);
        }

        // 5. Ring1 (1x1)
        int ring1X = equippedFrameX + Gui.getSlotSize() * 2 + (Gui.getSlotSize() / 2);
        int ring1Y = equippedFrameY + (equippedFrameHeight / 2) - (Gui.getSlotSize() * 2);
        renderFrame(g2d, ring1X, ring1Y, Gui.getSlotSize(), Gui.getSlotSize(), 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.ring1Slot).getStoredItem(), ring1X, ring1Y, false);
        if (ring1SlotPosition == null) ring1SlotPosition = new Position(ring1X, ring1Y);
        else
        {
            ring1SlotPosition.setX(ring1X);
            ring1SlotPosition.setY(ring1Y);
        }

        // 6. Ring2 (1x1)
        int ring2X = ring1X + Gui.getSlotSize() + (Gui.getSlotSize() / 4);
        int ring2Y = ring1Y;
        renderFrame(g2d, ring2X, ring2Y, Gui.getSlotSize(), Gui.getSlotSize(), 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.ring2Slot).getStoredItem(), ring2X, ring2Y, false);
        if (ring2SlotPosition == null) ring2SlotPosition = new Position(ring2X, ring2Y);
        else
        {
            ring2SlotPosition.setX(ring2X);
            ring2SlotPosition.setY(ring2Y);
        }

        // 7. Amulet (1x1)
        int amuletX = ring1X + (Gui.getSlotSize() / 2) + ((Gui.getSlotSize() / 4) / 2);
        int amuletY = ring1Y - Gui.getSlotSize() - (Gui.getSlotSize() / 4);
        renderFrame(g2d, amuletX, amuletY, Gui.getSlotSize(), Gui.getSlotSize(), 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.amuletSlot).getStoredItem(), amuletX, amuletY, false);
        if (amuletSlotPosition == null) amuletSlotPosition = new Position(amuletX, amuletY);
        else
        {
            amuletSlotPosition.setX(amuletX);
            amuletSlotPosition.setY(amuletY);
        }

        // 8. Shield (2x2)
        int shieldX = ring1X + ((Gui.getSlotSize() / 4) / 2);
        int shieldY = ring2Y + Gui.getSlotSize() + (Gui.getSlotSize() / 4);
        renderFrame(g2d, shieldX, shieldY, Gui.getSlotSize() * 2, Gui.getSlotSize() * 2, 0, 0, 1, 0.7f);
        renderInventoryItem(g2d, playerInventory.getEquipmentSlot(SlotType.shieldSlot).getStoredItem(), shieldX, shieldY, false);
        if (shieldSlotPosition == null) shieldSlotPosition = new Position(shieldX, shieldY);
        else
        {
            shieldSlotPosition.setX(shieldX);
            shieldSlotPosition.setY(shieldY);
        }

        g2d.dispose();
    }

    @Override
    public Slot getSlotAt(Position pos)
    {
        return null;
    }
}
