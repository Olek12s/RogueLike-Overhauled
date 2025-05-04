package main.utilities;


public enum DrawPriority
{
    Map(2),
    GROUND_ITEM(3),
    Entity(4),
    GUI(998),
    CURSOR(999);

    public final int value;


    /**
     * Higher value - larger priority upon drawing
     *
     * @param value
     */
    DrawPriority(int value) {this.value = value;}
}