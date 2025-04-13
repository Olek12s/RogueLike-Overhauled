package main.utilities;

import main.utilities.Direction;

public class Movement
{
    private Direction direction;
    private int movementSpeed;

    public Direction getDirection() {return direction;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public int getMovementSpeed() {return movementSpeed;}
    public void setMovementSpeed(int movementSpeed) {this.movementSpeed = movementSpeed;}
}
