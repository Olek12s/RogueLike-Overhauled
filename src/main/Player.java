package main;

public class Player extends Entity
{


    public Player()
    {
        super();
        System.out.println("created player object");

        renderer = new EntityRenderer(this);
        updater = new PlayerUpdater(this);
    }
}
