package main;

public class EntityUpdater implements IUpdatable
{
    private final Entity entity;

    public EntityUpdater(Entity entity)
    {
        this.entity = entity;
    }

    @Override
    public void update()
    {
        Position pos = new Position(entity.getWorldPosition().getX(), entity.getWorldPosition().getY());
        System.out.println(pos);
        entity.setWorldPosition(pos);
    }
}
