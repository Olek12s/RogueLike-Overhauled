package main.entity;

import main.GameController;
import main.IUpdatable;
import main.utilities.*;
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
        moveTowardsDirection();
        updateStatisticsMovementSpeed();
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
        if (!entity.isMoving()) return;
        Direction dir = entity.getMovement().getDirection();
        if (dir == null) return;

        Hitbox hitbox = entity.getHitbox();
        int speed = entity.getMovement().getCurrentMovementSpeed();
        int diag  = Math.max(1, (int)Math.round(speed/Math.sqrt(2)));
        int dx = 0, dy = 0;
        switch (dir) {
            case UP:         dy = -speed; break;
            case DOWN:       dy =  speed; break;
            case LEFT:       dx = -speed; break;
            case RIGHT:      dx =  speed; break;
            case UP_LEFT:    dx = -diag; dy = -diag; break;
            case UP_RIGHT:   dx =  diag; dy = -diag; break;
            case DOWN_LEFT:  dx = -diag; dy =  diag; break;
            case DOWN_RIGHT: dx =  diag; dy =  diag; break;
        }

        Position old = hitbox.getWorldPosition();
        Position next = new Position(old.getX() + dx, old.getY() + dy);
        Hitbox predicted = new Hitbox(next, hitbox.getWidth(), hitbox.getHeight());

        if (!Collisions.isHitboxCollidingWithWalls(predicted))
        {
            Movement.move(entity);
        }
        else
        {
            //TODO: precise movement near collidables for higher speeds
        }
    }

    public void updateStatisticsMovementSpeed()
    {
        int updatedMovementSpeed = entity.getMovement().getMaxMovementSpeed();
        /*
        for (Slot slot : equippedSlots)
        {
            if (slot.getStoredItem() == null) continue;
            updatedMovementSpeed /= (float)(slot.getStoredItem().getStatistics().getMovementSpeedPenalty());
        }

        Item storedItem = entity.getCurrentBeltSlot().getStoredItem();
        if (storedItem != null) updatedMovementSpeed /= (float)(storedItem.getStatistics().getMovementSpeedPenalty());
        */
        if (entity.isCrouching()) updatedMovementSpeed /= 3;
        entity.getMovement().setCurrentMovementSpeed(updatedMovementSpeed);
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
