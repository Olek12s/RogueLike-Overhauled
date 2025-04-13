package main.entity;

import main.GameController;
import main.IUpdatable;

public class EntityUpdater implements IUpdatable
{
    protected final Entity entity;

    public EntityUpdater(Entity entity)
    {
        this.entity = entity;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {

    }
}
