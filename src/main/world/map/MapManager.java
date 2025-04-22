package main.world.map;

import main.GameController;
import main.world.worldGeneration.IMapGenerationStrategy;
import main.world.worldGeneration.MapGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MapManager
{
    private static HashMap<MapID, Map> maps;
    private static MapID currentMapID;
    private static PrefferedMapSize prefferedMapSize;
    private MapGenerator mapGenerator;

    public static PrefferedMapSize getPrefferedMapSize() {return prefferedMapSize;}
    public static MapID getCurrentMapID() {return currentMapID;}
    public static Map getCurrentMap() {return getMapByID(currentMapID);}
    public static HashMap<MapID, Map> getMaps() {return maps;}
    public static Map getMapByID(MapID mapID) {return maps.get(mapID);}

    public MapManager()
    {
        maps = new HashMap<>();
        prefferedMapSize = PrefferedMapSize.MEDIUM;
        changeCurrentMap(MapID.SURFACE);
    }

    public static void putMap(MapID mapID, Map map)
    {
        maps.put(mapID, map);
    }

    public static void changeCurrentMap(MapID mapIDToChange)
    {
        if (!maps.containsKey(mapIDToChange))
        {
            Map newMap = new Map(mapIDToChange, prefferedMapSize);
            maps.put(mapIDToChange, newMap);
        }
        currentMapID = mapIDToChange;
    }
}
