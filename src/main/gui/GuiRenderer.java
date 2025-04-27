package main.gui;

import main.GameController;
import main.IDrawable;
import main.utilities.DrawPriority;

import java.awt.*;

public class GuiRenderer implements IDrawable
{
    private Gui gui;

    public GuiRenderer(Gui gui)
    {
        this.gui = gui;
        GameController.addDrawable(this);
    }

    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.GUI;
    }

    @Override
    public void draw(Graphics g2)
    {
        gui.getHealthBar().renderHealthBar(g2);
    }
}
