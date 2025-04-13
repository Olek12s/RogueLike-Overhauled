package main.world.worldGeneration;

import java.util.Random;

public class TerrainGenerator
{
    private int stepSize;
    private int width;
    private int height;
    private double scale;
    private float bias;
    private short[][] values;
    private Random random;

    public TerrainGenerator(int width, int height, int stepSize, double scale, float bias, long seed)
    {
        this.width = width;
        this.height = height;
        this.stepSize = stepSize;
        this.scale = scale;
        this.bias = bias;
        this.values = new short[width][height];
        this.random = new Random(seed);

        values[0][0] = (short) random.nextInt(256);
        values[0][height - 1] = (short) random.nextInt(256);
        values[width - 1][0] = (short) random.nextInt(256);
        values[width - 1][height - 1] = (short) random.nextInt(256);

        generateDiamondSquareHeightMap();
    }

    private void generateDiamondSquareHeightMap()
    {
        while (stepSize > 1)
        {
            diamondStep();
            squareStep();
            scale /= 2;
            stepSize /= 2;
        }
    }

    private void diamondStep()
    {
        int halfStep = stepSize / 2;
        for (int x = halfStep; x < width; x += stepSize)
        {
            for (int y = halfStep; y < height; y += stepSize)
            {
                float topLeft = values[x - halfStep][y - halfStep];
                float topRight = values[x + halfStep][y - halfStep];
                float bottomLeft = values[x - halfStep][y + halfStep];
                float bottomRight = values[x + halfStep][y + halfStep];

                float generatedValue = (topLeft + topRight + bottomLeft + bottomRight) / 4.0f;
                generatedValue += (random.nextFloat() * 2 - bias) * (float) scale;
                values[x][y] = (short) generatedValue;
            }
        }
    }

    private void squareStep()
    {
        int halfStep = stepSize / 2;
        for (int x = 0; x < width; x += halfStep)
        {
            for (int y = (x + halfStep) % stepSize; y < height; y += stepSize)
            {
                float generatedValue = 0;
                int count = 0;

                if (x - halfStep >= 0)
                {
                    generatedValue += values[x - halfStep][y];
                    count++;
                }
                if (x + halfStep < width)
                {
                    generatedValue += values[x + halfStep][y];
                    count++;
                }
                if (y - halfStep >= 0)
                {
                    generatedValue += values[x][y - halfStep];
                    count++;
                }
                if (y + halfStep < height)
                {
                    generatedValue += values[x][y + halfStep];
                    count++;
                }
                if (count > 0)
                {
                    generatedValue /= (float) count;
                }
                generatedValue += ((random.nextFloat() * 2 - bias) * (float) scale);
                values[x][y] = (short) generatedValue;
            }
        }
    }

    public short[][] getValues()
    {
        return values;
    }
}
