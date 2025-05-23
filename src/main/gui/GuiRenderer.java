package main.gui;

import main.GameController;
import main.Gamestate;
import main.IDrawable;
import main.entity.player.Player;
import main.item.Item;
import main.utilities.DrawPriority;
import main.utilities.sprite.Sprite;

import java.awt.*;

public class GuiRenderer implements IDrawable
{
    private Gui gui;

    public GuiRenderer(Gui gui)
    {
        this.gui = gui;
        GameController.addDrawable(this);
    }

    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.GUI;
    }

    @Override
    public void draw(Graphics g2)
    {
        if (GameController.getGameStateController().isInState(Gamestate.INVENTORY))
        {
            gui.getMainInvGui().renderMainInventory(g2);
            gui.getStatisticsGui().renderStatisticsFrame(g2);
            gui.getEquippedInvGui().renderEquippedFrame(g2);
            renderHeldItem(g2);
        }
        gui.getCraftingGui().renderCrafting(g2);
        gui.getBeltInvGui().renderInventorybelt(g2);
        //gui.getDebugInfoGui().renderDebugInfo(g2);
        gui.getHealthBar().renderHealthBar(g2);
    }

    public void renderHeldItem(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();
        Item heldItem = ((Player)GameController.getPlayer()).getHeldItem();
        if (heldItem == null) return;

        int itemSlotWidth = heldItem.getSlotWidth();
        int itemSlotHeight = heldItem.getSlotHeight();
        int itemPixelWidth = itemSlotWidth * Gui.getSlotSize();
        int itemPixelHeight = itemSlotHeight * Gui.getSlotSize();


        Sprite sprite = heldItem.getSprite();
        int spriteWidth = sprite.getImage().getWidth();
        int spriteHeight = sprite.getImage().getHeight();

        float scaleX = (float) itemPixelWidth / spriteWidth;
        float scaleY = (float) itemPixelHeight / spriteHeight;
        float scale = Math.min(scaleX, scaleY);

        int drawWidth = (int) (spriteWidth * scale);
        int drawHeight = (int) (spriteHeight * scale);

        int mouseX = GameController.getMouseHandler().getMousePosition().getX();
        int mouseY = GameController.getMouseHandler().getMousePosition().getY();

        int drawX = mouseX + (itemPixelWidth - drawWidth) / 2;
        int drawY = mouseY + (itemPixelHeight - drawHeight) / 2;

        g2d.drawImage(sprite.getImage(), drawX, drawY, drawWidth, drawHeight, null);
    }

    public static void renderFrame(Graphics g, int x, int y, int width, int height, int innerPadding, int outerWidth, int innerWidth, float opacity)
    {
        Graphics2D g2d = (Graphics2D)g.create();

        // rounding parameters
        int arcWidth = 10;
        int arcHeight = 10;

        // inner part of frame
        g2d.setStroke(new BasicStroke());
        g2d.setColor(new Color(0f, 0f, 0f, opacity));
        g2d.fillRoundRect(x, y, width, height, arcWidth, arcHeight);

        // outer part of frame
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(outerWidth));
        g2d.drawRoundRect(x, y, width, height, arcWidth, arcHeight);

        // middle part of frame
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(innerWidth));
        g2d.drawRoundRect(x + innerPadding, y + innerPadding, width  - (innerPadding*2), height - (innerPadding*2), arcWidth, arcHeight);
        // g2d.dispose();
    }

    /**
     *
     * @param g2d               - Graphics2D object
     * @param item              - Item to draw
     * @param inventoryX        - starting X of frame
     * @param inventoryY        - starting Y of frame
     * @param scaleToFitSlot    - should scale on draw to fit 1x1 slot (depending on item's width and height)
     */
    public static void renderInventoryItem(Graphics2D g2d, Item item, int inventoryX, int inventoryY, boolean scaleToFitSlot)
    {
        if (item == null) return;
        int itemSlotWidth = item.getSlotWidth();    // width of item
        int itemSlotHeight = item.getSlotHeight();  // height of item

        // counting new position and item's sprite size in pixels
        int itemPixelX = inventoryX;
        int itemPixelY = inventoryY;
        int itemPixelWidth = itemSlotWidth * Gui.getSlotSize();
        int itemPixelHeight = itemSlotHeight * Gui.getSlotSize();

        Sprite sprite = item.getSprite();
        int spriteWidth = sprite.getImage().getWidth();
        int spriteHeight = sprite.getImage().getHeight();

        if (scaleToFitSlot)    // scaling and centering
        {
            float scaleX = (float) itemPixelWidth / spriteWidth;
            float scaleY = (float) itemPixelHeight / spriteHeight;
            float scale = Math.min(scaleX, scaleY);
            int scaledSpriteWidth = (int) (spriteWidth * scale);
            int scaledSpriteHeight = (int) (spriteHeight * scale);

            int drawX = itemPixelX + (itemPixelWidth - scaledSpriteWidth) / 2;      // centering X
            int drawY = itemPixelY + (itemPixelHeight - scaledSpriteHeight) / 2;    // centering Y
            int drawWidth = scaledSpriteWidth/item.getSlotWidth();
            int drawHeight = scaledSpriteHeight/item.getSlotHeight();
            g2d.drawImage(sprite.getImage(), drawX, drawY, drawWidth, drawHeight, null);
        }
        else
        {
            g2d.drawImage(sprite.getImage(), itemPixelX, itemPixelY, itemPixelWidth, itemPixelHeight, null);
        }
    }
}