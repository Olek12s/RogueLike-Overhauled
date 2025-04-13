package main;

import main.utilities.DrawPriority;

import java.awt.*;

public interface IDrawable
{
    DrawPriority getDrawPriority();
    void draw(Graphics g2);
}
