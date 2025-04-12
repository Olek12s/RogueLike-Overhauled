package main;

import java.awt.*;

public class EntityRenderer implements IDrawable
{
    private final Entity entity;

    public EntityRenderer(Entity entity)
    {
        this.entity = entity;
        GameController.getInstance().getDrawables().add(this);
    }


    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.Entity;
    }

    @Override
    public void draw(Graphics g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect(entity.getWorldPosition().getX(), entity.getWorldPosition().getY(), 50, 50);
    }
}
