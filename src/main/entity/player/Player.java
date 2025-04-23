package main.entity.player;

import main.entity.Entity;
import main.entity.EntityID;
import main.entity.EntityRenderer;
import main.utilities.Hitbox;

public class Player extends Entity
{
    public Player(EntityID entityID)
    {
        super(entityID);
        setUpdater(new PlayerUpdater(this));
    }

    @Override
    public void setHitbox()
    {
        Hitbox h = new Hitbox(this.getWorldPosition(), this.getCurrentSprite(), 16, 16);
        setHitbox(h);
    }

    @Override
    public void setupStatistics()
    {
        getMovement().initMovementSpeed(10);
    }
}
