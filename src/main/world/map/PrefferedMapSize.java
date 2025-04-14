package main.world.map;

public enum PrefferedMapSize
{
    SMALL,
    MEDIUM,
    LARGE;


    public static int mapSizeToInteger(PrefferedMapSize prefferedMapSize, MapID mapID)
    {
        try
        {
            if (mapID == MapID.SURFACE)
            {
                if (prefferedMapSize == SMALL) return 513;
                else if (prefferedMapSize == MEDIUM) return 1025;
                else if (prefferedMapSize == LARGE) return 2049;
            }
            if (mapID == MapID.CAVE || mapID == MapID.DEEP_CAVE)
            {
                if (prefferedMapSize == SMALL) return 257;
                else if (prefferedMapSize == MEDIUM) return 513;
                else if (prefferedMapSize == LARGE) return 1025;
            }
            throw new IllegalArgumentException("Unsupported map size or map ID");
        }
        catch (IllegalArgumentException ex)
        {
            ex.printStackTrace();
            return -1;
        }
    }
}
