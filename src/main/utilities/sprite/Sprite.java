package main.utilities.sprite;

import main.camera.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Sprite
{
    public final int resolutionX;
    public final int resolutionY;
    public final List<BufferedImage> levels;

    public Sprite(BufferedImage image)
    {
        this.resolutionX = image.getWidth();
        this.resolutionY = image.getHeight();
        this.levels = new ArrayList<>();

        BufferedImage prev = image;
        levels.add(prev);

        // Generate all mipmap levels until (1, 1)
        while (prev.getWidth() > 1 || prev.getHeight() > 1)
        {
            int nextW = Math.max(1, prev.getWidth() / 2);
            int nextH = Math.max(1, prev.getHeight() / 2);
            BufferedImage next = new BufferedImage(nextW, nextH, prev.getType());

            Graphics2D g2 = next.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(prev, 0, 0, nextW, nextH, null);
            g2.dispose();

            levels.add(next);
            prev = next;
        }
    }

    public BufferedImage getImage()
    {
        double scale = Camera.getScaleFactor();
        if (scale <= 0) scale = 1.0;

        // count minmap level: floor(log2(1/scale))
        double inv = 1.0 / scale;
        int level = (int)Math.floor(Math.log(inv) / Math.log(2));
        level = Math.max(0, Math.min(levels.size() - 1, level));
        return levels.get(level);
    }
}
