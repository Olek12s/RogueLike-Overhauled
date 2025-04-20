package main.entity;

import main.utilities.DrawPriority;
import main.GameController;
import main.IDrawable;
import main.utilities.Position;
import main.camera.Camera;

import java.awt.*;

public class EntityRenderer implements IDrawable
{
    private final Entity entity;

    public EntityRenderer(Entity entity)
    {
        this.entity = entity;
        GameController.addDrawable(this);
    }


    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.Entity;
    }

    @Override
    public void draw(Graphics g2) {
        g2.setColor(Color.GREEN);
        Position screenPosition = Camera.toScreenPosition(entity.getWorldPosition());

        g2.fillRect(screenPosition.getX(), screenPosition.getY(), 64, 64);
    }
}
