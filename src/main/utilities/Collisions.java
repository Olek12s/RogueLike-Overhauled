package main.utilities;

import main.entity.Entity;
import main.world.map.MapManager;

public class Collisions
{
    public static boolean isHitboxCollidingWithWalls(Hitbox hitbox)
    {
        Position hbPos = hitbox.getWorldPosition();

        MapManager.getCurrentMap();

        //check 4 corners + middle of hitbox
        try
        {
            boolean isUpLeftColliding = MapManager.getCurrentMap().getTile(hitbox.getWorldPosition()).isCollidable();
            boolean isUpRightColliding = MapManager.getCurrentMap().getTile(new Position(hbPos.getX() + hitbox.getWidth(), hbPos.getY())).isCollidable();
            boolean isDownRightColliding = MapManager.getCurrentMap().getTile(new Position(hbPos.getX() + hitbox.getWidth(), hbPos.getY() + hitbox.getHeight())).isCollidable();
            boolean isDownLeftColliding = MapManager.getCurrentMap().getTile(new Position(hbPos.getX(), hbPos.getY() + hitbox.getHeight())).isCollidable();
            boolean isMiddleColliding = MapManager.getCurrentMap().getTile(hitbox.getCenterWorldPosition()).isCollidable();


            /*
            if ((isUpLeftColliding || isUpRightColliding || isDownRightColliding || isDownLeftColliding || isMiddleColliding))
            {
                System.out.println("collision");
            }
            */
            return (isUpLeftColliding || isUpRightColliding || isDownRightColliding || isDownLeftColliding || isMiddleColliding);
        }
        catch (IndexOutOfBoundsException ex)    // out of map
        {
            return true;
        }
    }

    public static boolean isHitboxCollidingWith(Entity entity)
    {
        return false;
    }
}
