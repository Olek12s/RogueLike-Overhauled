package main.utilities;

public enum Direction
{
    DOWN,
    LEFT,
    RIGHT,
    UP,
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT;

    public static int directionToVariation(Direction direction)
    {
        return switch (direction) {
            case DOWN       -> 0;
            case LEFT       -> 1;
            case RIGHT      -> 2;
            case UP         -> 3;
            case UP_LEFT    -> 4;
            case UP_RIGHT   -> 5;
            case DOWN_LEFT  -> 6;
            case DOWN_RIGHT -> 7;
            default         -> 0;
        };
    }
}