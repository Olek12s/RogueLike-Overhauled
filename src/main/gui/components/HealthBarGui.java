package main.gui.components;

import main.GameController;
import main.gui.Gui;
import main.utilities.sprite.Sprite;
import main.utilities.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HealthBarGui
{
    private SpriteSheet spriteSheet;
    private Sprite currentSprite;

    public HealthBarGui()
    {
        try
        {
            this.spriteSheet = new SpriteSheet(ImageIO.read(new File("resources/hud/Hearts.png")), 27, 27);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void updateHealthBar()
    {
        int currentHealth = GameController.getPlayer().getStatistics().getCurrentHealth();
        int maxHealth = GameController.getPlayer().getStatistics().getMaxHealth();

        double healthRatio = (double) currentHealth / maxHealth;

        int tick = 0;
        if (healthRatio < 0.8) tick = 1;
        if (healthRatio < 0.6) tick = 2;
        if (healthRatio < 0.4) tick = 3;
        if (healthRatio < 0.2) tick = 4;

        this.currentSprite = spriteSheet.extractSprite(tick, 0);
    }

    public void renderHealthBar(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

      //  if (hud.gc.player.statistics.getHitPoints() < 0) hud.gc.player.statistics.setHitPoints(0);
        int healthPercent = (int) ((GameController.getPlayer().getStatistics().getCurrentHealth() / (double) GameController.getPlayer().getStatistics().getMaxHealth()) * 100);

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect((int)(Gui.getScale()/1.4f), (int)(Gui.getScale()/5f), Gui.getScale()*4, (int)(Gui.getScale()/2.6f));
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int)(Gui.getScale()/1.4f), (int)(Gui.getScale()/5f), Gui.getScale()*4, (int)(Gui.getScale()/2.6f));
        g2d.setColor(Color.RED);
        g2d.fillRect((int)(Gui.getScale()/1.4f)+1, (int)(Gui.getScale()/5f)+1, (healthPercent * Gui.getScale()*4) / 100, (int)(Gui.getScale()/2.6f)-1);

        g2d.drawImage(currentSprite.getImage(), 0, 0, Gui.getScale(), Gui.getScale(), null);

    }
}
