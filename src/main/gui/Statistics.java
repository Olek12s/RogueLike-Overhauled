package main.gui;

import main.inventory.Inventory;

import java.awt.*;

public class Statistics
{
    public static void renderStatisticsFrame(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();
        boolean playerNextLevel = hud.gc.player.statistics.getExp() >= hud.gc.player.statistics.getNextLevelExp();


        int width = hud.gc.getWidth();
        int height = hud.gc.getHeight();

        int beltSlotCount = Inventory.INVENTORY_BELT_SLOTS;
        int beltY = beltPosition.y - height / 2;

        int totalWidth = Inventory.INVENTORY_WIDTH_SLOTS * slotSize;    // width of main inventory
        int totalHeight =  Inventory.INVENTORY_HEIGHT_SLOTS * slotSize;  // height of main inventory

        float statsToInvRatio = 0.62f;
        int statsFrameWidth = (int) (statsToInvRatio * totalWidth);
        int statsFrameHeight = totalHeight + slotSize;

        int statsFrameX = mainInventoryFramePosition.x - statsFrameWidth;
        int statsFrameY = mainInventoryFramePosition.y;

        if (statsFrameX < 0)
        {

            statsFrameWidth = mainInventoryFramePosition.x;
            statsFrameX = 0;
            if (statsFrameWidth < 0)
            {
                statsFrameWidth = 0;
            }
        }

        renderFrame(g2d, statsFrameX, statsFrameY, statsFrameWidth, statsFrameHeight, 3, 3, 1, 0.7f);



        EntityStatistics stats = hud.gc.player.statistics;

        int hp = stats.getHitPoints();
        int maxHp = stats.getMaxHitPoints();
        int mana = stats.getMana();
        int maxMana = stats.getMaxMana();
        int regen = stats.getRegeneration();
        int movementSpeed = stats.getCurrentMovementSpeed();
        int maxMovementSpeed = stats.getMaxMovementSpeed();
        int armour = stats.getArmour();
        int magicArmour = stats.getMagicArmour();
        int strength = stats.getStrength();
        int dexterity = stats.getDexterity();
        int intellect = stats.getIntellect();
        int stamina = stats.getStamina();
        int exp = stats.getExp();
        int nextLevelExp = stats.getNextLevelExp();

        String[] statTexts = {
                "HP: " + hp + " / " + maxHp,
                "Mana: " + mana + " / " + maxMana,
                "Armour: " + armour,
                "Magic Armour: " + magicArmour,
                "Strength: " + strength,
                "Stamina: " + stamina,
                "Regeneration: " + regen,
                "Dexterity: " + dexterity,
                "Intellect: " + intellect,
                "Movement Speed: " + movementSpeed + " / " + maxMovementSpeed,
                "EXP: " + exp + " / " + nextLevelExp
        };

        g2d.setColor(Color.WHITE);
        g2d.setFont(HUDFont);

        FontMetrics fm = g2d.getFontMetrics(HUDFont);

        int leftMargin = 10;
        int iconTextSpacing = 5;
        int iconSize = scaledFontSize;

        int textX = statsFrameX + leftMargin + iconSize + iconTextSpacing;
        int textY = statsFrameY + 30;

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
            int currentY = textY + i * (scaledFontSize + 5);
            if (playerNextLevel && i < statTexts.length - 2)    // don't render 2 last buttons
            {
                int iconX = statsFrameX + leftMargin;
                int iconY = currentY - fm.getAscent() + (fm.getHeight() - iconSize) / 2;
                g2d.drawImage(hud.levelUp.image, iconX, iconY, iconSize, iconSize, null);
                levelUpIconPositions[i] = new Position(iconX, iconY);
            }
            g2d.drawString(statTexts[i], textX, currentY);
        }

        g2d.dispose();
    }
}
