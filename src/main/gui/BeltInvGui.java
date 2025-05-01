package main.gui;

import main.GameController;
import main.inventory.Inventory;
import main.item.Item;

import java.awt.*;

public class BeltInvGui
{
    public void renderInventorybelt(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2;
        int slotCount = Inventory.INVENTORY_BELT_SLOTS;
        int totalWidth = slotCount * Gui.getSlotSize();

        int marginFromBottom = 10;
        int beltX = (GameController.getInstance().getWidth() - totalWidth) / 2;
        int beltY = GameController.getInstance().getHeight() - Gui.getSlotSize() - marginFromBottom;

        /*
        if (beltPosition == null) beltPosition = new Position(beltX, beltY);
        else
        {
            beltPosition.x = beltX;
            beltPosition.y = beltY;
        }
        */

        for (int i = 0; i < slotCount; i++)
        {
            int frameX = beltX + i * Gui.getSlotSize();
            int frameY = beltY;


            if (i == GameController.getPlayer().getInventory().getCurrentSlotIdx())   // highlight currently selected slot
            {
                GuiRenderer.renderFrame(g2d, frameX, frameY, Gui.getSlotSize(), Gui.getSlotSize(), 3, 6, 3, 0.5f);
            }
            else GuiRenderer.renderFrame(g2d, frameX, frameY, Gui.getSlotSize(), Gui.getSlotSize(), 3, 3, 1, 0.5f);
        }

        for (int i = 0; i < GameController.getPlayer().getInventory().getBeltSlots().length; i++)
        {
            Item item = GameController.getPlayer().getInventory().getBeltSlots()[i].getStoredItem();
            if (item != null)
            {
                int x = beltX + i * Gui.getSlotSize();
                GuiRenderer.renderInventoryItem(g2d, item, x, beltY, true);
            }
        }
    }
}
