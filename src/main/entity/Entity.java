package main.entity;

import main.utilities.Direction;
import main.utilities.Movement;
import main.utilities.Position;
import main.utilities.sprite.Sprite;
import main.world.map.Chunk;

public abstract class Entity
{
    private final int ANIMATION_SPEED = 8;
    private EntityRenderer renderer;
    private EntityUpdater updater;
    private Movement movement;
    private Position worldPosition = new Position(0, 0);
    private EntityID entityID;
    private Chunk currentChunk;
    private boolean isMoving;

    //Abstracts//
    public abstract void setHitbox();
    public abstract void setupStatistics();
    //Abstracts//



    public Entity(EntityID entityID)
    {
        this.renderer = new EntityRenderer(this);
        this.updater = new EntityUpdater(this);
        this.movement = new Movement();
        this.entityID = entityID;
        setHitbox();
        setupStatistics();
    }

    public EntityRenderer getRenderer() {return renderer;}
    public EntityUpdater getUpdater() {return updater;}
    public Movement getMovement() {return movement;}
    public Position getWorldPosition() {return worldPosition;}
    public EntityID getEntityID() {return entityID;}
    public Sprite getCurrentSprite() {return renderer.getCurrentSprite();}
    public Chunk getCurrentChunk() {return currentChunk;}
    public void setCurrentChunk(Chunk chunk) {this.currentChunk = chunk;}
    public boolean isMoving() {return isMoving;}
    public void setMoving(boolean moving) {isMoving = moving;}
    public int getANIMATION_SPEED() {return ANIMATION_SPEED;}
    public void setDirection(Direction direction) {this.movement.setDirection(direction);}

    public void setRenderer(EntityRenderer renderer) {this.renderer = renderer;}
    public void setUpdater(EntityUpdater updater) {this.updater = updater;}

    public void setWorldPosition(Position worldPosition) {
        this.worldPosition = worldPosition;
    }
}
