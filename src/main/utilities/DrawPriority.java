package main.utilities;


public enum DrawPriority
{
    One(1),
    Two(2),
    Entity(3),
    Map(4);

    public final int value;


    /**
     * Higher value - larger priority upon drawing
     *
     * @param value
     */
    DrawPriority(int value) {this.value = value;}
}