package main.gui;

import main.GameController;
import main.entity.Statistics;
import main.inventory.Inventory;
import main.utilities.Movement;
import main.utilities.Position;
import main.utilities.sprite.Sprite;
import main.utilities.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static main.gui.GuiRenderer.renderFrame;

public class StatisticsGui
{
    private static Position[] levelUpIconPositions;
    private static Sprite levelUp;

    public StatisticsGui()
    {
        try
        {
            levelUp = new Sprite(ImageIO.read(new File("resources/hud/LevelUp.png")));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void renderStatisticsFrame(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();
        boolean playerNextLevel = GameController.getPlayer().getStatistics().getExp() >= GameController.getPlayer().getStatistics().getNextLevelExp();

        int width = GameController.getInstance().getWidth();
        int height = GameController.getInstance().getHeight();


        int invSlotsW = Inventory.INVENTORY_WIDTH_SLOTS;
        int invSlotsH = Inventory.INVENTORY_HEIGHT_SLOTS;
        int invTotalW = invSlotsW * Gui.getSlotSize();
        int invTotalH = invSlotsH * Gui.getSlotSize();
        int mainX = (width  - invTotalW ) / 2;
        int mainY = (height - invTotalH) / 2;

        // statistics frame size
        float statsToInvRatio = 0.62f;
        int statsFrameWidth = (int) (statsToInvRatio * invTotalW);
        int statsFrameHeight = invTotalH + Gui.getSlotSize();

        // position stats left of main inventory, with padding
        //int padding = Gui.getSlotSize() / 2
        int padding = 0;
        int statsFrameX = mainX - statsFrameWidth - padding;
        int statsFrameY = mainY;

        // clamp to screen if too far left
        if (statsFrameX < 0)
        {
            statsFrameX = padding;
        }

        renderFrame(g2d, statsFrameX, statsFrameY, statsFrameWidth, statsFrameHeight, 3, 3, 1, 0.7f);

        // draw stats text and icons
        Statistics stats = GameController.getPlayer().getStatistics();
        Movement movement = GameController.getPlayer().getMovement();

        String[] statTexts = {
                "HP: " + stats.getCurrentHealth() + " / " + stats.getMaxHealth(),
                "Mana: " + stats.getMana() + " / " + stats.getMaxMana(),
                "Armour: " + stats.getArmour(),
                "Magic Armour: " + stats.getMagicArmour(),
                "Strength: " + stats.getStrength(),
                "Stamina: " + stats.getStamina(),
                "Regeneration: " + stats.getRegeneration(),
                "Dexterity: " + stats.getDexterity(),
                "Intellect: " + stats.getIntellect(),
                "Movement Speed: " + movement.getCurrentMovementSpeed() + " / " + movement.getMaxMovementSpeed(),
                "EXP: " + stats.getExp() + " / " + stats.getNextLevelExp()
        };

        g2d.setColor(Color.WHITE);
        g2d.setFont(Gui.getFont());
        FontMetrics fm = g2d.getFontMetrics(Gui.getFont());

        int leftMargin = 10;
        int iconTextSpacing = 5;
        int iconSize = Gui.getScaledFontSize();

        int textX = statsFrameX + leftMargin + iconSize + iconTextSpacing;
        int textY = statsFrameY + Gui.getScaledFontSize() + 10;

        if (playerNextLevel)
        {
            levelUpIconPositions = new Position[statTexts.length];
        }
        else
        {
            levelUpIconPositions = null;
        }

        for (int i = 0; i < statTexts.length; i++)
        {
            int currentY = textY + i * (Gui.getScaledFontSize() + 5);
            if (playerNextLevel && i < statTexts.length - 2)
            {
                int iconX = statsFrameX + leftMargin;
                int iconY = currentY - fm.getAscent() + (fm.getHeight() - iconSize) / 2;
                g2d.drawImage(levelUp.getImage(), iconX, iconY, iconSize, iconSize, null);
                levelUpIconPositions[i] = new Position(iconX, iconY);
            }
            g2d.drawString(statTexts[i], textX, currentY);
        }

        g2d.dispose();
    }
}
