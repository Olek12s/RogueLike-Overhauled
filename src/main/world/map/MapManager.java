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
    private static MapID currentMap;
    private static PrefferedMapSize prefferedMapSize;

    public static PrefferedMapSize getPrefferedMapSize() {return prefferedMapSize;}
    public static MapID getCurrentMap() {return currentMap;}
    public static HashMap<MapID, Map> getMaps() {return maps;}
    public static Map getMapByID(MapID mapID) {return maps.get(mapID);}

    public static void putMap(MapID mapID, Map map)
    {
        maps.put(mapID, map);
    }

    public static void changeCurrentMap(MapID mapIDToChange)
    {
        currentMap = mapIDToChange;
    }

    /*
    public static void createMap(MapID mapID)
    {
        if (!maps.containsKey(mapID))
        {
            int mapSize = PrefferedMapSize.mapSizeToInteger(prefferedMapSize, mapID);
            IMapGenerationStrategy generationStrategy = MapID.getGenerationStrategy(mapID);
            Map generatedMap = new Map(MapGenerator.generateMap(mapSize, mapSize, generationStrategy));
        }
    }
    */
}
