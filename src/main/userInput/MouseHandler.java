package main.userInput;

import main.IUpdatable;
import main.camera.Camera;

import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener, IUpdatable
{

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("click");
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

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {

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
