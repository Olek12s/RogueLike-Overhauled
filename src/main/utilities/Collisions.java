package main.utilities;

import main.entity.Entity;
import main.world.map.MapManager;

import java.awt.*;

public class Collisions
{
    public static boolean checkCollisionsWithWalls(Entity entity)
    {
        Hitbox hitbox = entity.getHitbox();
        Position hbPos = hitbox.getWorldPosition();
        Direction direction = entity.getMovement().getDirection();
        int movementSpeed = entity.getMovement().getCurrentMovementSpeed();

        MapManager.getCurrentMap();

        //check 4 corners + middle of hitbox
        try
        {
            boolean isUpLeftColliding = MapManager.getCurrentMap().getTile(hitbox.getWorldPosition()).isCollidable();
            boolean isUpRightColliding = MapManager.getCurrentMap().getTile(new Position(hbPos.getX() + hitbox.getWidth(), hbPos.getY())).isCollidable();
            boolean isDownRightColliding = MapManager.getCurrentMap().getTile(new Position(hbPos.getX() + hitbox.getWidth(), hbPos.getY() + hitbox.getHeight())).isCollidable();
            boolean isDownLeftColliding = MapManager.getCurrentMap().getTile(new Position(hbPos.getX() + hitbox.getWidth(), hbPos.getY() + hitbox.getHeight())).isCollidable();
            boolean isMiddleColliding = MapManager.getCurrentMap().getTile(hitbox.getCenterWorldPosition()).isCollidable();

            if (isUpLeftColliding) System.out.println("up-left");
            if (isUpRightColliding) System.out.println("up-right");
            if (isDownLeftColliding) System.out.println("down-left");
            if (isDownRightColliding) System.out.println("down-right");
            if (isMiddleColliding) System.out.println("middle");
            System.out.println(MapManager.getCurrentMap().getTile(hitbox.getCenterWorldPosition()));
            return (isUpLeftColliding || isUpRightColliding || isDownRightColliding || isDownLeftColliding || isMiddleColliding);
        }
        catch (IndexOutOfBoundsException ex)    // out of map
        {
            return true;
        }
    }

    public static boolean checkCollisionsWithHitboxes(Entity entity)
    {
        return false;
    }
}
