package main.utilities;

import main.entity.Entity;
import main.utilities.sprite.Sprite;

import java.awt.*;

public class Hitbox
{
    private Rectangle hitboxRect;
    private Position worldPosition;

    public Hitbox(Position worldPosition, int width, int height)
    {
        this.worldPosition = worldPosition;
        hitboxRect = new Rectangle(0, 0, width, height);
    }

    public Hitbox(Position worldPosition, Sprite sprite, int hitboxWidth, int hitboxHeight)
    {
        this.worldPosition = worldPosition;
        int offsetX = (sprite.resolutionX - hitboxWidth) / 2;
        int offsetY = (sprite.resolutionY - hitboxHeight) / 2;
        this.hitboxRect = new Rectangle(offsetX, offsetY, hitboxWidth, hitboxHeight);
    }

    public Rectangle getHitboxRect() {
        return new Rectangle(
                worldPosition.getX() + hitboxRect.x,
                worldPosition.getY() + hitboxRect.y,
                hitboxRect.width,
                hitboxRect.height
        );
    }

    public Position getCenterWorldPosition()
    {
        int centerX = worldPosition.getX() + (hitboxRect.width/2);
        int centerY = worldPosition.getY() + (hitboxRect.height/2);

        Position centerPosition = new Position(centerX, centerY);

        return centerPosition;
    }



    public boolean isInsideHitbox(Position point)
    {
        Position hitboxCenter = getCenterWorldPosition();
        int halfWidth = hitboxRect.width/2;
        int halfHeight = hitboxRect.height/2;

        return point.getX() >= hitboxCenter.getX() - halfWidth &&
                point.getX() <= hitboxCenter.getX() + halfWidth &&
                point.getY() >= hitboxCenter.getY() - halfHeight &&
                point.getY() <= hitboxCenter.getY() + halfHeight;
    }


    @Override
    public String toString()
    {
        return "[" +  hitboxRect.getBounds().x + " " + hitboxRect.getBounds().y + "] [" + hitboxRect.getWidth() + ", " + hitboxRect.getHeight() + "]";
    }
}