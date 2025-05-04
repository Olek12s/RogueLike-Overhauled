package main.gui.cursor;

import main.GameController;
import main.Gamestate;
import main.IDrawable;
import main.utilities.DrawPriority;
import main.utilities.Position;

import java.awt.*;

public class CursorRenderer implements IDrawable
{
    CursorGui cursor;

    public CursorRenderer(CursorGui cursor)
    {
        this.cursor = cursor;
        GameController.addDrawable(this);
    }


    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.CURSOR;
    }

    @Override
    public void draw(Graphics g2) {
        if (!GameController.getGameStateController().isInState(Gamestate.INVENTORY)) {return;}

        Position pos = cursor.getCursorPosition();
        int x = pos.getX();
        int y = pos.getY();

        if (x >= 0 && y >= 0)
        {
            g2.setColor(Color.LIGHT_GRAY);

            // Vertical line
            g2.fillRect(x - 1, y - 10, 3, 21);

            // Horizontal line
            g2.fillRect(x - 10, y - 1, 21, 3);
        }
    }

}
