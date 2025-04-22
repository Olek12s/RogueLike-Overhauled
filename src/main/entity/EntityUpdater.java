package main.entity;

import main.GameController;
import main.IUpdatable;
import main.utilities.Direction;
import main.utilities.Movement;
import main.utilities.sprite.SpriteSheet;
import main.world.map.Chunk;
import main.world.map.MapManager;

public class EntityUpdater implements IUpdatable
{
    protected final Entity entity;
    private int spriteCounter;

    public EntityUpdater(Entity entity)
    {
        this.entity = entity;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {
        updateChunkAssociation();
        updateCurrentSprite();
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

    public void moveTowardsDirection()
    {
        if (entity.isMoving())
        {
            Movement.move(entity);
        }
    }



    public void updateCurrentSprite()
    {
        SpriteSheet sheet = EntityRenderer.getSpriteSheetByID(entity.getEntityID());
        int totalTicks = sheet.getAnimationTicks();
        int speed = entity.getANIMATION_SPEED();
        Direction dir = entity.getMovement().getDirection();

        if (entity.isMoving() && dir != null)
        {
            spriteCounter = (spriteCounter + 1) % (totalTicks * speed);
            int tick = spriteCounter / speed;
            tick = Math.min(tick, totalTicks - 1);
            int variation = Direction.directionToVariation(dir);
            entity.getRenderer().setAnimationTick(tick);
            entity.getRenderer().setSpriteVariation(variation);
        }
        else
        {
            spriteCounter = 0;
            int variation;
            if (entity.getMovement().getDirection() != null)
            {
                variation = Direction.directionToVariation(entity.getMovement().getDirection());
            }
            else
            {
                variation = 0;
            }
            entity.getRenderer().setAnimationTick(0);
            entity.getRenderer().setSpriteVariation(variation);
        }
    }
}
