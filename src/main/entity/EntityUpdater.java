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

    public void moveTowardsDirection() {
        if (!entity.isMoving() || entity.getMovement().getDirection() == null) return;
        Hitbox hitbox = entity.getHitbox();
        Movement mov = entity.getMovement();
        int speed = mov.getCurrentMovementSpeed();
        int diag = Math.max(1, (int)Math.round(speed/Math.sqrt(2)));
        int dx = 0, dy = 0;
        switch (mov.getDirection()) {
            case UP        -> dy = -speed;
            case DOWN      -> dy =  speed;
            case LEFT      -> dx = -speed;
            case RIGHT     -> dx =  speed;
            case UP_LEFT   -> { dx = -diag; dy = -diag; }
            case UP_RIGHT  -> { dx =  diag; dy = -diag; }
            case DOWN_LEFT -> { dx = -diag; dy =  diag; }
            case DOWN_RIGHT-> { dx =  diag; dy =  diag; }
        }
        Position nextPos = new Position(hitbox.getWorldPosition().getX() + dx,
                hitbox.getWorldPosition().getY() + dy);
        Hitbox predictedHB = new Hitbox(nextPos, hitbox.getWidth(), hitbox.getHeight());
        if (!Collisions.isHitboxCollidingWithWalls(predictedHB)) {
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
