package main;

public class Entity
{
    private EntityRenderer renderer;
    private EntityUpdater updater;


    private Movement movement;
    private Position worldPosition;

    public Entity()
    {
        this.renderer = new EntityRenderer(this);
        this.updater = new EntityUpdater(this);
    }

    public EntityRenderer getRenderer() {return renderer;}
    public EntityUpdater getUpdater() {return updater;}
    public Movement getMovement() {return movement;}
    public Position getWorldPosition() {return worldPosition;}

    public void setWorldPosition(Position worldPosition) {
        this.worldPosition = worldPosition;
    }
}
