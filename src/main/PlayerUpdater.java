package main;

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

        if (KeyHandler.isW_PRESSED()) entity.setWorldPosition(new Position(entity.worldPosition.getX(), entity.worldPosition.getY()-1));
        if (KeyHandler.isA_PRESSED()) entity.setWorldPosition(new Position(entity.worldPosition.getX()-1, entity.worldPosition.getY()));
        if (KeyHandler.isS_PRESSED()) entity.setWorldPosition(new Position(entity.worldPosition.getX(), entity.worldPosition.getY()+1));
        if (KeyHandler.isD_PRESSED()) entity.setWorldPosition(new Position(entity.worldPosition.getX()+1, entity.worldPosition.getY()));
    }


}
