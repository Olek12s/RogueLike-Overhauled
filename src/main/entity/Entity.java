package main.entity;

import main.utilities.Movement;
import main.utilities.Position;
import main.utilities.sprite.Sprite;
import main.world.map.Chunk;

public abstract class Entity
{
    private EntityRenderer renderer;
    private EntityUpdater updater;
    private Movement movement;
    private Position worldPosition = new Position(0, 0);
    private EntityID entityID;
    private Chunk currentChunk;

    //Abstracts//
    public abstract void setHitbox();
    public abstract void setupStatistics();
    //Abstracts//



    public Entity(EntityID entityID)
    {
        this.renderer = new EntityRenderer(this);
        this.updater = new EntityUpdater(this);
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

    public void setRenderer(EntityRenderer renderer) {this.renderer = renderer;}
    public void setUpdater(EntityUpdater updater) {this.updater = updater;}

    public void setWorldPosition(Position worldPosition) {
        this.worldPosition = worldPosition;
    }
}
