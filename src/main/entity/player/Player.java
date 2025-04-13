package main.entity.player;

import main.entity.Entity;
import main.entity.EntityRenderer;

public class Player extends Entity
{


    public Player()
    {
        super();

        renderer = new EntityRenderer(this);
        updater = new PlayerUpdater(this);
    }
}
