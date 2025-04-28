package main.gui;

import main.GameController;
import main.inventory.Inventory;
import main.item.Item;
import main.utilities.Position;

import java.awt.*;

public class MainInv
{


    public static void renderMainInventory(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();

        //window size
        int width = GameController.getInstance().getWidth();
        int height = GameController.getInstance().getHeight();

        int widthSlots = Inventory.INVENTORY_WIDTH_SLOTS;
        int heightSlots = Inventory.INVENTORY_HEIGHT_SLOTS;

        int totalWidth = widthSlots * Gui.getSlotSize();
        int totalHeight = heightSlots * Gui.getSlotSize();

        int beltSlotCount = Inventory.INVENTORY_BELT_SLOTS;
        int beltTotalWidth = beltSlotCount * Gui.getSlotSize();
        //int beltY = beltPosition.y - height/2;    //TODO: c


        // set main inventory position above inventory bar
        int inventoryFrameX = (width - totalWidth) / 2;
        int inventoryFrameY = beltY - totalHeight;

        /*
        if (mainInventoryFramePosition == null) mainInventoryFramePosition = new Position(inventoryFrameX, inventoryFrameY);
        else
        {
            mainInventoryFramePosition.x = inventoryFrameX;
            mainInventoryFramePosition.y = inventoryFrameY;
        }
        */

        // Drawing every inventory slot
        for (int i = 0; i < widthSlots; i++)
        {
            for (int j = 0; j < heightSlots; j++)
            {
                int slotX = inventoryFrameX + i * Gui.getSlotSize();
                int slotY = inventoryFrameY + j * Gui.getSlotSize();

                GuiRenderer.renderFrame(g2d, slotX, slotY, Gui.getSlotSize(), Gui.getSlotSize(), 0, 0, 1, 0.7f);
            }
        }

        // Drawing item sprites in main inventory
        for (int i = 0; i < GameController.getPlayer().getInventory().getMainInventoryItemList().size(); i++)
        {
            Item item = GameController.getPlayer().getInventory().getMainInventoryItemList().get(i);
            if (item != null)
            {
                Position pos = item.getInventoryPosition();
                int itemX = inventoryFrameX + pos.getX() * Gui.getSlotSize();
                int itemY = inventoryFrameY + pos.getY() * Gui.getSlotSize();

                GuiRenderer.renderInventoryItem(g2d, item, itemX, itemY, false);
            }
        }
    }




    public MainInv()
    {

    }
}
