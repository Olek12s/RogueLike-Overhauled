package main.world.worldGeneration;

import main.world.map.MapID;
import main.world.worldGeneration.strategies.SurfaceGenerationStrategy;

public class MapGenerator
{
    //private IMapGenerationStrategy generationStrategy;
    private static long seed = System.currentTimeMillis();
    public static long getSeed() {return seed;}
    public static void setSeed(long seed) {MapGenerator.seed = seed;}
    //public void setSeed(long seed) {this.seed = seed;}

    public static short[][] generateMap(int width, int height, MapID mapID)
    {
        IMapGenerationStrategy generationStrategy = MapID.getGenerationStrategy(mapID);
        return generationStrategy.generate(width, height, seed);
    }

    public static short[][] generateMap(int width, int height, IMapGenerationStrategy generationStrategy)
    {
        return generationStrategy.generate(width, height, seed);
    }
}
