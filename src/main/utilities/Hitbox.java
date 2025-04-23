package main.utilities;

import main.entity.Entity;
import main.utilities.sprite.Sprite;

import java.awt.*;

public class Hitbox
{
    private Position worldPosition;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    public Position getWorldPosition() {
        return new Position(
                worldPosition.getX() + offsetX,
                worldPosition.getY() + offsetY
        );
    }
    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public Rectangle getHitboxRect() {
        Position topLeft = getWorldPosition();
        return new Rectangle(
                topLeft.getX(),
                topLeft.getY(),
                width,
                height
        );
    }

    public Hitbox(Position worldPosition, int width, int height)
    {
        this.worldPosition = worldPosition;
        this.width = width;
        this.height = height;
    }

    public Hitbox(Position worldPosition, Sprite sprite, int hitboxWidth, int hitboxHeight)
    {
        offsetX = (sprite.resolutionX - hitboxWidth) / 2;
        offsetY = (sprite.resolutionY - hitboxHeight) / 2;
        this.worldPosition = worldPosition;
        this.width = hitboxWidth;
        this.height = hitboxHeight;
    }



    public Position getCenterWorldPosition() {
        int centerX = worldPosition.getX() + width / 2;
        int centerY = worldPosition.getY() + height / 2;
        return new Position(centerX, centerY);
    }



    public boolean isInsideHitbox(Position point) {
        Position center = getCenterWorldPosition();
        int halfW = width / 2;
        int halfH = height / 2;
        return point.getX() >= center.getX() - halfW &&
                point.getX() <= center.getX() + halfW &&
                point.getY() >= center.getY() - halfH &&
                point.getY() <= center.getY() + halfH;
    }


    @Override
    public String toString()
    {
        return String.format(
                "Hitbox[pos=(%d,%d), size=(%d×%d)]",
                worldPosition.getX(), worldPosition.getY(),
                width, height
        );
    }
}