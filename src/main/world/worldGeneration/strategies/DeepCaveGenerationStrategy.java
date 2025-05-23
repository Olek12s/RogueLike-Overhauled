package main.world.worldGeneration.strategies;

import main.world.worldGeneration.IMapGenerationStrategy;
import main.world.worldGeneration.TerrainGenerator;

import static java.lang.Math.clamp;

public class DeepCaveGenerationStrategy implements IMapGenerationStrategy
{
    //GENERATOR VARIABLES
    private final int stepSize = 128;
    private final int scale = 128;
    private short[][] mapValues;
    private short[][] map1;
    private short[][] map2;
    private int width;
    private int height;
    //GENERATOR VARIABLES


    @Override
    public short[][] generate(int width, int height, long seed)
    {
        this.width = width;
        this.height = height;
        mapValues = new short[width][height];

        TerrainGenerator ds1 = new TerrainGenerator(width, height, stepSize/4, scale, 1f, seed);
        TerrainGenerator ds2 = new TerrainGenerator(width, height, stepSize/2, scale*2, 1f, seed);
        map1 = ds1.getValues();
        map2 = ds2.getValues();

        parseValues();
        return mapValues;
    }

    private void parseValues()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                float val1 = map1[x][y] * 0.8f;
                float val2 = map2[x][y] * 0.1f;
                double val = Math.abs(val1 + val2 * 20.0);
                val = clamp(val, 0, 255);
                val = 255.0 - val;
                if (val >= 170)
                {
                    mapValues[x][y] = 0;
                }
                else
                {
                    mapValues[x][y] = 255;
                }
            }
        }
    }
}
