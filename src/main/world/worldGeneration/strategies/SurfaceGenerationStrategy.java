package main.world.worldGeneration.strategies;

import main.world.worldGeneration.IMapGenerationStrategy;
import main.world.worldGeneration.MapGenerator;
import main.world.worldGeneration.TerrainGenerator;

public class SurfaceGenerationStrategy extends MapGenerator implements IMapGenerationStrategy
{
    //GENERATOR VARIABLES
    private final int stepSize = 128;
    private final int scale = 32;
    private short[][] mapValues;
    private short[][] map1;
    private short[][] map2;
    private short[][] map3;
    private int width;
    private int height;
    //GENERATOR VARIABLES

    public SurfaceGenerationStrategy(IMapGenerationStrategy generationStrategy)
    {
        super(generationStrategy);
    }

    @Override
    public short[][] generate(int width, int height, long seed)
    {
        this.width = width;
        this.height = height;
        this.mapValues = new short[width][height];

        TerrainGenerator ds1 = new TerrainGenerator(width, height, stepSize*2,  scale-6, 0.90f, seed);
        TerrainGenerator ds2 = new TerrainGenerator(width, height, stepSize/2, scale+8, 0.89f, seed+1);
        TerrainGenerator ds3 = new TerrainGenerator(width, height, stepSize/2, scale/2, 0.90f, seed+1);
        map1 = ds1.getValues();
        map2 = ds2.getValues();
        map3 = ds3.getValues();

        parseValues();
        return mapValues;
    }

    protected void parseValues()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float val1 = map1[x][y] * 0.8f;
                float val2 = map2[x][y] * 0.9f;
                float val3 = map3[x][y] * 0.7f;
                double val = Math.max(val1+8 + 64.0 + val2*2-16, val2) + 64 + val3*3;
                double factor = distanceFactorFromEdge(width, height, x, y);
                val = Math.max(val, val2);
                val = val * factor*0.75f;

                if (val <= 35) val = 35;
                else if (val > 35 && val <= 40) val = 70;
                else if (val > 40 && val <= 90) val = 150;
                else if (val > 90 && val <= 255) val = 255;
                mapValues[x][y] = (short)val;
            }
        }
    }

    private double distanceFactorFromEdge(int width, int height, int x, int y)
    {
        double centerX = (width - 1) / 2.0;
        double centerY = (height - 1) / 2.0;
        float strength = 1.07f; // how strong distance factor is

        double dx = x - centerX;
        double dy = y - centerY;
        double dist = Math.sqrt(dx * dx + dy * dy);

        double maxDist = Math.sqrt(centerX/strength * centerX/strength + centerY/strength * centerY/strength);

        double factor = 1.0 - (dist / maxDist);
        if (factor < 0.0) factor = 0.0;
        if (factor > 1.0) factor = 1.0;
        return factor;
    }
}


