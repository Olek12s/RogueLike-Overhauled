package main.entity.player;

import main.entity.Entity;
import main.entity.EntityID;
import main.entity.EntityRenderer;
import main.entity.Statistics;
import main.item.ingredients.BlueFlower;
import main.utilities.Hitbox;
import main.utilities.Position;

public class Player extends Entity
{
    public Player(EntityID entityID)
    {
        super(entityID);
        setUpdater(new PlayerUpdater(this));
        this.getWorldPosition().setXY(-300,1700);
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
        setStatistics(new Statistics(100));
    }
}
