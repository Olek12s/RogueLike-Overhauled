package main.userInput;

import main.GameController;
import main.IUpdatable;
import main.camera.Camera;
import main.utilities.Position;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener, IUpdatable
{
    private static Position mousePosition;
    private static boolean leftButtonClicked = false;
    private static boolean leftButtonReleased = true;



    public MouseHandler() {
        mousePosition = new Position(GameController.getInstance().getWidth() / 2, GameController.getInstance().getHeight() / 2);
        GameController.addUpdatable(this);
    }


    public static boolean isLeftButtonClicked() {return leftButtonClicked;}
    public static void setLeftButtonClicked(boolean value) {leftButtonClicked = value;}
    public static boolean isLeftButtonReleased() {return leftButtonReleased;}

    public static Position getMousePosition()
    {
        return mousePosition;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftButtonClicked = true;
            leftButtonReleased = false;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        leftButtonClicked = false;
        leftButtonReleased = true;
    }

    @Override public void mouseDragged(MouseEvent e)
    {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }
    @Override public void mouseMoved(MouseEvent e)
    {
        mousePosition.setX(e.getX());
        mousePosition.setY(e.getY());
    }
    @Override public void mouseWheelMoved(MouseWheelEvent e)
    {
        Camera.changeCameraZoom(e.getWheelRotation());
    }
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    @Override
    public void update()
    {

    }
}