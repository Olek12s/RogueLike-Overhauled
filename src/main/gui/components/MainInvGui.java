package main.gui.components;

import main.GameController;
import main.gui.ClickableSlots;
import main.gui.Gui;
import main.gui.GuiRenderer;
import main.inventory.Inventory;
import main.inventory.Slot;
import main.item.Item;
import main.utilities.Position;

import java.awt.*;

public class MainInvGui implements ClickableSlots
{
    public void renderMainInventory(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();

        //window size
        int width = GameController.getInstance().getWidth();
        int height = GameController.getInstance().getHeight();

        int widthSlots = Inventory.INVENTORY_WIDTH_SLOTS;
        int heightSlots = Inventory.INVENTORY_HEIGHT_SLOTS;

        int totalWidth = widthSlots * Gui.getSlotSize();
        int totalHeight = heightSlots * Gui.getSlotSize();;

        int inventoryFrameX = (width  - totalWidth ) / 2;
        int inventoryFrameY = (height - totalHeight) / 2;

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

    public MainInvGui()
    {

    }

    @Override
    public Slot getSlotAt(Position pos)
    {
        return null;
    }
}
