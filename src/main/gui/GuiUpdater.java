package main.gui;

import main.GameController;
import main.IUpdatable;

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

        gui.getHealthBar().updateHealthBar();
    }

    private static void updateProportions()
    {
        Gui.setScaleX(GameController.getInstance().getWidth() / 16);
        Gui.setScaleY(GameController.getInstance().getHeight() / 9);
        Gui.setScale(Math.min(Gui.getScaleX(), Gui.getScaleY()));
    }
}
