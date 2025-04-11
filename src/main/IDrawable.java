package main;

import java.awt.*;

public interface IDrawable
{
    int getDrawPriority();
    void draw(Graphics g2);
}
