package main.world.map;

import main.GameController;
import main.IUpdatable;

public class MapUpdater implements IUpdatable
{
    private Map map;

    public MapUpdater(Map map)
    {
        this.map = map;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {

    }
}
