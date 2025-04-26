package main.entity;

import main.GameController;
import main.IUpdatable;
import main.utilities.Collisions;
import main.utilities.Direction;
import main.utilities.Movement;
import main.utilities.sprite.SpriteSheet;
import main.world.map.Chunk;
import main.world.map.MapManager;

public class EntityUpdater implements IUpdatable
{
    protected final Entity entity;
    private int spriteCounter;
    private boolean wasMoving = false;

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
            Collisions.checkCollisionsWithWalls(entity);
            Movement.move(entity);
        }
    }


    public void updateCurrentSprite()
    {
        SpriteSheet sheet = EntityRenderer.getSpriteSheetByID(entity.getEntityID());
        int totalTicks = sheet.getAnimationTicks();
        int speed = entity.getANIMATION_SPEED();
        Direction dir = entity.getMovement().getDirection();
        boolean isCurrentlyMoving = entity.isMoving() && dir != null;

        if (isCurrentlyMoving)
        {
            if (!wasMoving)
            {
                spriteCounter = speed;
            }
            else
            {
                spriteCounter = (spriteCounter + 1) % (totalTicks * speed);
            }

            int tick = spriteCounter / speed;
            tick = Math.min(tick, totalTicks - 1);
            int variation = Direction.directionToVariation(dir);
            entity.getRenderer().setAnimationTick(tick);
            entity.getRenderer().setSpriteVariation(variation);
        }
        else
        {
            spriteCounter = 0;
            int variation = (dir != null) ? Direction.directionToVariation(dir) : 0;
            entity.getRenderer().setAnimationTick(0);
            entity.getRenderer().setSpriteVariation(variation);
        }
        wasMoving = isCurrentlyMoving;
    }
}
