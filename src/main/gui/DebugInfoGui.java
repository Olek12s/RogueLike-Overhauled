package main.gui;

import main.GameController;
import main.world.tile.Tile;

import java.awt.*;

public class DebugInfoGui
{
    private static int renderDebugInfoCounter;
    private static String renderTime;
    private static String updateTime;
    private static String summaryTime;

    public void renderDebugInfo(Graphics g2)
    {
        renderDebugInfoLeftTop(g2);
        renderDebugInfoLeft(g2);
        renderFPSTopRight(g2);
    }

    public void renderDebugInfoLeft(Graphics g2)
    {
        renderDebugInfoCounter++;
        if (renderDebugInfoCounter >= 60)
        {
            updateTimers();
            renderDebugInfoCounter = 0;
        }

        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.WHITE);
        g2d.setFont(Gui.getFont());

        int baseX = 10;
        int baseY = (int)(GameController.getInstance().getHeight()/1.8f);
        int scaledX = (int) (baseX * (Gui.getScale() / 64.0));
        int scaledY = baseY;

        String debugInfo = getTimers();
        String[] debugLines = debugInfo.split("\\n");

        for (int i = 0; i < debugLines.length; i++)
        {
            g2d.drawString(debugLines[i], scaledX, scaledY + i * (Gui.getScaledFontSize() + 5));
        }
    }
    public void renderFPSTopRight(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2.create();

        int x = GameController.getInstance().getWidth() - GameController.getInstance().getWidth() / 11;
        int y = 40;


        g2d.setColor(Color.WHITE);
        g2d.setFont(Gui.getFont());

        long totalTimePerFrame = GameController.getInstance().getRenderTime() + GameController.getInstance().getUpdateTime();
        int fpsVal = totalTimePerFrame > 0 ? (int) (1_000_000_000L / totalTimePerFrame) : 0;
        fpsVal = Math.min(GameController.getInstance().getTargetDrawFrame(), fpsVal);

        String fps = String.format("FPS: %d", fpsVal);
        g2d.drawString(fps, x, y);

    }

    public void renderDebugInfoLeftTop(Graphics g2)
    {
        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.WHITE);
        g2d.setFont(Gui.getFont());

        int baseX = 10;
        int baseY = 80;
        int scaledX = (int) (baseX * (Gui.getScale() / 64.0));
        int scaledY = (int) (baseY * (Gui.getScale() / 64.0));

        int positionX = GameController.getCamera().getPositionToFocus().getX();
        int positionY = GameController.getCamera().getPositionToFocus().getY();

        String cameraPosX = "X: " + positionX / Tile.getTileSize() + "  " + positionX;
        String cameraPosY = "Y: " + positionY / Tile.getTileSize() + "  " + positionY;

        g2d.drawString(cameraPosX, scaledX, scaledY);
        g2d.drawString(cameraPosY, scaledX, scaledY + (Gui.getScaledFontSize() + 5));
    }

    private void updateTimers()
    {
        GameController gc = GameController.getInstance();

        float renderMs = gc.getRenderTime() / 1_000_000f;
        float logicMs  = gc.getUpdateTime()  / 1_000_000f;


        float frameMsDraw  = 1000f / gc.getTargetDrawFrame();
        float frameMsLogic = 1000f / gc.getTargetLogicFrame();


        float pctRender = renderMs / frameMsDraw * 100f;
        float pctLogic  = logicMs  / frameMsLogic * 100f;


        float summaryMs  = renderMs + logicMs;
        float summaryPct = pctRender + pctLogic;


        renderTime  = String.format("Render time: %.2f ms (%.2f%%)", renderMs, pctRender);
        updateTime  = String.format("Update time: %.2f ms (%.2f%%)", logicMs,  pctLogic);
        summaryTime = String.format("Summary time: %.2f ms (%.2f%%)", summaryMs, summaryPct);
    }
    private String getTimers()
    {
        return renderTime + "\n" + updateTime + "\n" + summaryTime;
    }
}
