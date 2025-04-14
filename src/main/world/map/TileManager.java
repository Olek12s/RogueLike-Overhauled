package main.world.map;

public class TileManager
{
    public static int getHealthByID(TileID ID)
    {
        switch(ID)
        {
            case DEFAULT: return Integer.MAX_VALUE;
            default: return 0;
        }
    }
}
