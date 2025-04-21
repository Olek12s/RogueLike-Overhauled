package main.entity.player;

import main.utilities.Position;
import main.entity.Entity;
import main.entity.EntityUpdater;
import main.userInput.KeyHandler;

public class PlayerUpdater extends EntityUpdater
{
    public PlayerUpdater(Entity entity)
    {
        super(entity);
    }

    @Override
    public void update()
    {
        super.update();

        Position pos = entity.getWorldPosition();

        if (KeyHandler.isW_PRESSED()) pos.setY(pos.getY() - 10);
        if (KeyHandler.isA_PRESSED()) pos.setX(pos.getX() - 10);
        if (KeyHandler.isS_PRESSED()) pos.setY(pos.getY() + 10);
        if (KeyHandler.isD_PRESSED()) pos.setX(pos.getX() + 10);
    }
}
