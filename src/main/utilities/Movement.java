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
        Position previousPosition = entity.getWorldPosition();
        int newX = previousPosition.getX();
        int newY = previousPosition.getY();
        int speed = entity.getMovement().getCurrentMovementSpeed();
        Direction direction = entity.getMovement().getDirection();

        int diagSpeed = Math.max(1, (int)Math.round(speed / Math.sqrt(2)));
        if (direction != null)
        {
            switch (direction)
            {
                case UP -> newY -= speed;
                case DOWN -> newY += speed;
                case LEFT -> newX -= speed;
                case RIGHT -> newX += speed;
                case UP_LEFT ->
                {
                    newX -= diagSpeed;
                    newY -= diagSpeed;
                }
                case UP_RIGHT ->
                {
                    newX += diagSpeed;
                    newY -= diagSpeed;
                }
                case DOWN_LEFT ->
                {
                    newX -= diagSpeed;
                    newY += diagSpeed;
                }
                case DOWN_RIGHT ->
                {
                    newX += diagSpeed;
                    newY += diagSpeed;
                }
                default ->
                {
                    // no movement
                }
            }
        }

        Position expectedPosition = new Position(newX, newY);
        entity.setWorldPosition(expectedPosition);
        System.out.println(entity.getMovement().getCurrentMovementSpeed());
    }
}
