package main.gui.cursor;


import main.GameController;
import main.utilities.Position;

public class CursorGui
{
    private Position cursorPosition;
    private CursorRenderer cursorRenderer;
    private CursorUpdater cursorUpdater;

    public Position getCursorPosition() {return cursorPosition;}
    public void setCursorPosition(Position cursorPosition) {this.cursorPosition = cursorPosition;}


    public CursorGui()
    {
        cursorPosition = new Position(GameController.getInstance().getWidth(), GameController.getInstance().getHeight());
        cursorRenderer = new CursorRenderer(this);
        cursorUpdater = new CursorUpdater(this);
    }
}
