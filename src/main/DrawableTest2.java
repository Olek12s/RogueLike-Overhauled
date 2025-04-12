package main;

import java.awt.*;

public class DrawableTest2 implements IDrawable
{

    public DrawableTest2()
    {
        GameController.getInstance().getDrawables().add(this);
    }

    @Override
    public DrawPriority getDrawPriority() {
        return DrawPriority.Two;
    }

    @Override
    public void draw(Graphics g2) {
        g2.setColor(Color.RED);
        g2.drawRect(30, 30, 50, 50);
    }
}
