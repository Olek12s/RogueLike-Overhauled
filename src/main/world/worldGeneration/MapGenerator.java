package main.world.worldGeneration;

public class MapGenerator
{
    private IMapGenerationStrategy generationStrategy;
    private static long seed;

    public IMapGenerationStrategy getGenerationStrategy() {return generationStrategy;}
    public void setGenerationStrategy(IMapGenerationStrategy generationStrategy) {this.generationStrategy = generationStrategy;}
    public static long getSeed() {return seed;}
    //public void setSeed(long seed) {this.seed = seed;}

    public MapGenerator(IMapGenerationStrategy generationStrategy)
    {
        this.generationStrategy = generationStrategy;
        MapGenerator.seed = System.currentTimeMillis();
    }

    public short[][] generateMap(int width, int height)
    {
        return generationStrategy.generate(width, height, seed);
    }
}
