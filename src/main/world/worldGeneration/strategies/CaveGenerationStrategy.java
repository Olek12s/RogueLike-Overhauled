package main.world.worldGeneration.strategies;

import main.world.worldGeneration.IMapGenerationStrategy;
import main.world.worldGeneration.TerrainGenerator;

public class CaveGenerationStrategy implements IMapGenerationStrategy
{
    //GENERATOR VARIABLES
    private final int stepSize = 256;
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

        TerrainGenerator ds1 = new TerrainGenerator(width, height, stepSize / 2, scale / 2, 0.82f, seed);
        TerrainGenerator ds2 = new TerrainGenerator(width, height, stepSize / 2, scale / 2, 0.89f, seed);
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
                float val1 = map1[x][y];
                float val2 = map2[x][y];


                double val = (((val1+150) * val1-170) % 190) * 5;
                val = Math.min(val, val2);
                val = (val * 2 + 190);
                val = Math.clamp(val, 0, 255);
                mapValues[x][y] = (short)val;


                if (mapValues[x][y] >= 250) val = 0;
                else if (mapValues[x][y] >= 240 && mapValues[x][y] <= 243) val = 140;
                else if (mapValues[x][y] >= 215 && mapValues[x][y] <= 245) val = 0;
                else if (mapValues[x][y] >= 190 && mapValues[x][y] <= 195) val = 140;
                else if (mapValues[x][y] >= 195 && mapValues[x][y] <= 200) val = 0;
                else if (mapValues[x][y] >= 200 && mapValues[x][y] <= 206) val = 140;
                else if (mapValues[x][y] >= 150 && mapValues[x][y] <= 200) val = 140;
                else val = 255;
                mapValues[x][y] = (short)val;
            }
        }
    }
}
