package main.gui.cursor;


import main.GameController;
import main.Gamestate;
import main.IUpdatable;

public class CursorUpdater implements IUpdatable
{
    private CursorGui cursor;

    public CursorUpdater(CursorGui cursor)
    {
        this.cursor = cursor;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {
        if (!GameController.getGameStateController().isInState(Gamestate.INVENTORY))
        {
            return;
        }

        updateCursorPosition();
    }

    private void updateCursorPosition()
    {
        cursor.getCursorPosition().setX(GameController.getMouseHandler().getMousePosition().getX());
        cursor.getCursorPosition().setY(GameController.getMouseHandler().getMousePosition().getY());
    }
}
