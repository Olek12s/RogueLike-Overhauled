package main.world.map;

public enum TileID
{
    DEFAULT(0);

    private final int id;

    TileID(int id) {this.id = id;}

    public int getId() {return id;}

    public static TileID fromId(int id)
    {
        for (TileID tileID : values())
        {
            if (tileID.getId() == id)
            {
                return tileID;
            }
        }
        throw new IllegalArgumentException("Illegal ID");
    }
}
