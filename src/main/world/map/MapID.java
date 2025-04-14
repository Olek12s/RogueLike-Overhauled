package main.world.map;

import main.world.worldGeneration.IMapGenerationStrategy;
import main.world.worldGeneration.strategies.CaveGenerationStrategy;
import main.world.worldGeneration.strategies.DeepCaveGenerationStrategy;
import main.world.worldGeneration.strategies.SurfaceGenerationStrategy;

public enum MapID
{
    SURFACE,
    CAVE,
    DEEP_CAVE;

    public static IMapGenerationStrategy getGenerationStrategy(MapID mapID)
    {
        return switch (mapID)
        {
            case SURFACE -> new SurfaceGenerationStrategy();
            case CAVE -> new CaveGenerationStrategy();
            case DEEP_CAVE -> new DeepCaveGenerationStrategy();
            default -> null;
        };
    }
}
