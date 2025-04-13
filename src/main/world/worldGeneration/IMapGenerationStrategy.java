package main.world.worldGeneration;

public interface IMapGenerationStrategy
{
    short[][] generate(int width, int height, long seed);
}
