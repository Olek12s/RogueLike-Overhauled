package main;

public class EntityUpdater implements IUpdatable
{
    protected final Entity entity;

    public EntityUpdater(Entity entity)
    {
        this.entity = entity;
        GameController.getInstance().getUpdatables().add(this);
    }

    @Override
    public void update()
    {

    }
}
