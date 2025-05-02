package main.item;

import main.GameController;
import main.IDrawable;
import main.camera.Camera;
import main.utilities.DrawPriority;
import main.utilities.Position;
import main.world.map.Chunk;
import main.world.map.MapManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class ItemRenderer implements IDrawable
{
    private Item item;

    public ItemRenderer(Item item)
    {
        GameController.addDrawable(this);
        this.item = item;
    }

    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.GROUND_ITEM;
    }

    public void draw(Graphics g2)
    {
        BufferedImage img = item.getSprite().getImage();
        Position wp = item.getWorldPosition();
        int worldW = img.getWidth();
        int worldH = img.getHeight();

        if (!Camera.isVisibleOnScreen(wp, worldW, worldH))
        {
            return;
        }

        Position sp = Camera.toScreenPosition(wp);
        double scale = Camera.getScaleFactor();
        int w = (int) Math.ceil(img.getWidth()  * scale);
        int h = (int) Math.ceil(img.getHeight() * scale);
        g2.drawImage(img, sp.getX(), sp.getY(), w, h, null);
        System.out.println("render");
    }
}
