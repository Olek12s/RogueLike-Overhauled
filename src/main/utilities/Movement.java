package main.utilities;

import main.entity.Entity;

public class Movement
{
    private Direction direction;
    private int currentMovementSpeed;
    private int maxMovementSpeed;

    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public int getCurrentMovementSpeed() {return currentMovementSpeed;}
    public void setCurrentMovementSpeed(int currentMovementSpeed) {this.currentMovementSpeed = currentMovementSpeed;}
    public int getMaxMovementSpeed() {return maxMovementSpeed;}
    public void setMaxMovementSpeed(int maxMovementSpeed) {this.maxMovementSpeed = maxMovementSpeed;}
    public void initMovementSpeed(int movementSpeed)
    {
        this.currentMovementSpeed = movementSpeed;
        this.maxMovementSpeed = movementSpeed;
    }

    public static void move(Entity entity)
    {
        Position pos = entity.getWorldPosition();
        int speed = entity.getMovement().getCurrentMovementSpeed();
        Direction dir = entity.getMovement().getDirection();
        int diagSpeed = Math.max(1, (int)Math.round(speed / Math.sqrt(2)));

        int dx = 0, dy = 0;
        if (dir != null)
        {
            switch (dir)
            {
                case UP         -> dy = -speed;
                case DOWN       -> dy =  speed;
                case LEFT       -> dx = -speed;
                case RIGHT      -> dx =  speed;
                case UP_LEFT    ->
                {
                    dx = -diagSpeed;
                    dy = -diagSpeed;
                }
                case UP_RIGHT   ->
                {
                    dx =  diagSpeed;
                    dy = -diagSpeed;
                }
                case DOWN_LEFT  ->
                {
                    dx = -diagSpeed;
                    dy =  diagSpeed;
                }
                case DOWN_RIGHT ->
                {
                    dx =  diagSpeed;
                    dy =  diagSpeed;
                }
            }
            pos.setX(pos.getX() + dx);
            pos.setY(pos.getY() + dy);
        }
    }
}
