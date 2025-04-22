package main.entity.player;

import main.entity.Entity;
import main.entity.EntityID;
import main.entity.EntityRenderer;

public class Player extends Entity
{
    public Player(EntityID entityID)
    {
        super(entityID);

        setRenderer(new EntityRenderer(this));
        setUpdater(new PlayerUpdater(this));
    }

    @Override
    public void setHitbox()
    {

    }

    @Override
    public void setupStatistics()
    {

    }
}
