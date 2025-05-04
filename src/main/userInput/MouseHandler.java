package main.userInput;

import main.GameController;
import main.IUpdatable;
import main.camera.Camera;
import main.utilities.Position;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener, IUpdatable
{
    Position mousePosition;

    public Position getMousePosition() {
        return mousePosition;
    }

    public MouseHandler()
    {
        mousePosition = new Position(GameController.getInstance().getWidth()/2, GameController.getInstance().getHeight()/2);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        Camera.changeCameraZoom(e.getWheelRotation());
    }

    @Override
    public void update()
    {

    }
}
