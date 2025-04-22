package main.utilities;

import main.entity.Entity;
import main.utilities.sprite.Sprite;

import java.awt.*;

public class Hitbox
{
    private Rectangle hitboxRect;
    private Position worldPosition;

    public Rectangle getHitboxRect() {return hitboxRect;}
    public Position getWorldPosition() {return worldPosition;}
    public void setWorldPosition(Position worldPosition)
    {
        this.worldPosition = worldPosition;
        this.hitboxRect.x = worldPosition.getX();
        this.hitboxRect.y = worldPosition.getY();
    }
    public void setHitboxRect(Rectangle hitboxRect) {this.hitboxRect = hitboxRect;}
    public int getWidth() {return hitboxRect.width;}
    public void setWidth(int width)
    {
        this.hitboxRect.setSize(width, hitboxRect.height);
    }
    public int getHeight() {return hitboxRect.height;}
    public void setHeight(int height)
    {
        this.hitboxRect.setSize(hitboxRect.width, height);
    }
    public Position getCenterWorldPosition()
    {
        int centerX = worldPosition.getX() + (hitboxRect.width/2);
        int centerY = worldPosition.getY() + (hitboxRect.height/2);

        Position centerPosition = new Position(centerX, centerY);

        return centerPosition;
    }

    public double getDiagonalLength()
    {
        int width = hitboxRect.width;
        int height = hitboxRect.height;
        return Math.sqrt(width * width + height * height);
    }

    public Hitbox(Position worldPosition, int width, int height)
    {
        hitboxRect = new Rectangle(worldPosition.getX(), worldPosition.getY(), width, height);
        this.worldPosition = worldPosition;
    }

    public void centerPositionToEntity(Entity entity)
    {

        int entityX = entity.getWorldPosition().getX();
        int entityY = entity.getWorldPosition().getY();

        Sprite currentSprite = entity.getCurrentSprite();
        float spriteWidth = currentSprite.resolutionX;
        float spriteHeight = currentSprite.resolutionY;


        int spriteCenterX = entityX + (int)(spriteWidth / 2);
        int spriteCenterY = entityY + (int)(spriteHeight / 2);

        int x = spriteCenterX - (int)(hitboxRect.getWidth() / 2);
        int y = spriteCenterY - (int)(hitboxRect.getHeight() / 2);
        hitboxRect.x = x;
        hitboxRect.y = y;
        worldPosition.setX(x);
        worldPosition.setY(y);
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