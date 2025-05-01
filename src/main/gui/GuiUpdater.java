package main.gui;

import main.GameController;
import main.IUpdatable;

import java.awt.*;

public class GuiUpdater implements IUpdatable
{

    private Gui gui;

    public GuiUpdater(Gui gui)
    {
        this.gui = gui;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {
        updateProportions();
        updateSizes();

        gui.getHealthBar().updateHealthBar();
    }

    private static void updateProportions()
    {
        Gui.setScaleX(GameController.getInstance().getWidth() / 16);
        Gui.setScaleY(GameController.getInstance().getHeight() / 9);
        Gui.setScale(Math.min(Gui.getScaleX(), Gui.getScaleY()));
    }

    public static void updateSizes()
    {
        int newFontSize = (int) (Gui.getBaseFontSize() * Gui.getScale() / 64);
        Gui.setScaledFontSize(newFontSize);

        Font currentFont = Gui.getFont();
        if (currentFont == null || currentFont.getSize() != newFontSize)
        {
            Gui.setFont(new Font("Monospaced", Font.BOLD, newFontSize));
        }

        Gui.setSlotSize((Gui.getBaseSlotSize() * Gui.getScale()) / 64);
    }
}
