package main.entity;

import main.GameController;
import main.IUpdatable;
import main.world.map.Chunk;
import main.world.map.MapManager;

public class EntityUpdater implements IUpdatable
{
    protected final Entity entity;

    public EntityUpdater(Entity entity)
    {
        this.entity = entity;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {
        updateChunkAssociation();
    }

    public void updateChunkAssociation()
    {
        Chunk currentChunk = entity.getCurrentChunk();
        Chunk newChunk = MapManager.getCurrentMap().getChunk(entity.getWorldPosition());

        if (newChunk != currentChunk)   // move entity to the new chunk
        {
            if (currentChunk != null)
            {
                currentChunk.removeEntity(entity);
            }
            newChunk.addEntity(entity);
            entity.setCurrentChunk(newChunk);
        }
    }
}
