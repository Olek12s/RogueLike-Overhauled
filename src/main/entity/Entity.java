package main.entity;

import main.utilities.Movement;
import main.utilities.Position;

public class Entity
{
    protected EntityRenderer renderer;
    protected EntityUpdater updater;


    protected Movement movement;
    protected Position worldPosition = new Position(0, 0);

    //temp
    public int getWidth() {return 25;}
    public int getHeight() {return 25;}
    //temp

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
