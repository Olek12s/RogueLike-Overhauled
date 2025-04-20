package main.world.map;

import main.GameController;
import main.IDrawable;
import main.utilities.DrawPriority;

import java.awt.*;

public class Maprenderer implements IDrawable
{
    private Map map;

    public Maprenderer(Map map)
    {
        this.map = map;
        GameController.addDrawable(this);
    }

    @Override
    public DrawPriority getDrawPriority() {
        return DrawPriority.Map;
    }

    @Override
    public void draw(Graphics g2) {

    }
}
