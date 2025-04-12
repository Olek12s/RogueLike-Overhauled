package main;

import java.awt.*;

public class DrawableTest1 implements IDrawable
{

    public DrawableTest1()
    {
        GameController.getInstance().getDrawables().add(this);
    }

    @Override
    public DrawPriority getDrawPriority() {
        return DrawPriority.One;
    }

    @Override
    public void draw(Graphics g2) {
        g2.setColor(Color.BLUE);
        g2.drawRect(50, 50, 50, 50);
    }
}
