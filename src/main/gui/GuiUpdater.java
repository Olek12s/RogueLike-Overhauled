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

    public void updateSizes()
    {
        //scaledFontSize = (int) (baseFontSize * Gui.getScale() / 64);
        //HUDFont = new Font("Monospaced", Font.BOLD, scaledFontSize);
        gui.setSlotSize((gui.getBaseSlotSize() * Gui.getScale()) / 64);
    }
}
