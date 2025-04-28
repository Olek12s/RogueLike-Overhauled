package main.gui;

import main.GameController;
import main.world.tile.Tile;

import java.awt.*;

public class DebugInfo
{
    private static int renderDebugInfoCounter;

    public static void renderDebugInfoLeft(Graphics g2)
    {
        renderDebugInfoCounter++;
        if (renderDebugInfoCounter >= 60)
        {
            updateTimers();
            updateDrawCount();
            renderDebugInfoCounter = 0;
        }

        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.WHITE);
        g2d.setFont(HUDFont);

        int baseX = 10;
        int baseY = (int)(GameController.getInstance().getHeight()/1.8f);
        int scaledX = (int) (baseX * (Gui.getScale() / 64.0));
        int scaledY = baseY;

        String debugInfo = getTimers() + "\n" + drawCount;
        String[] debugLines = debugInfo.split("\\n");

        for (int i = 0; i < debugLines.length; i++)
        {
            g2d.drawString(debugLines[i], scaledX, scaledY + i * (scaledFontSize + 5));
        }
    }
    public static void renderFPSTopRight(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();

        int x = GameController.getInstance().getWidth() - GameController.getInstance().getWidth() / 11;
        int y = 40;


        g2d.setColor(Color.WHITE);
        g2d.setFont(HUDFont);

        long totalTimePerFrame = GameController.getInstance().getRenderTime() + GameController.getInstance().getUpdateTime();
        int fpsVal = totalTimePerFrame > 0 ? (int) (1_000_000_000L / totalTimePerFrame) : 0;
        fpsVal = Math.min(GameController.getInstance().getTargetDrawFrame(), fpsVal);

        String fps = String.format("FPS: %d", fpsVal);
        g2d.drawString(fps, x, y);

    }

    public static void renderDebugInfoLeftTop(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.WHITE);
        g2d.setFont(HUDFont);

        int baseX = 10;
        int baseY = 80;
        int scaledX = (int) (baseX * (Gui.getScale() / 64.0));
        int scaledY = (int) (baseY * (Gui.getScale() / 64.0));

        int positionX = hud.gc.camera.getCameraPosition().x;
        int positionY = hud.gc.camera.getCameraPosition().y;

        String cameraPosX = "X: " + positionX / Tile.getTileSize() + "  " + positionX;
        String cameraPosY = "Y: " + positionY / Tile.getTileSize() + "  " + positionY;

        g2d.drawString(cameraPosX, scaledX, scaledY);
        g2d.drawString(cameraPosY, scaledX, scaledY + (scaledFontSize + 5));
    }
}
