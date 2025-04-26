package main.entity.player;

import main.utilities.Direction;
import main.utilities.Position;
import main.entity.Entity;
import main.entity.EntityUpdater;
import main.userInput.KeyHandler;

public class PlayerUpdater extends EntityUpdater
{
    Entity playerEntity;
    public PlayerUpdater(Entity entity)
    {
        super(entity);
        this.playerEntity = entity;
    }

    @Override
    public void update()
    {
        super.update();
        updatePlayerDirection();
    }

    private void updatePlayerDirection()
    {
        playerEntity.setMoving(false);
        if (KeyHandler.isW_PRESSED() && KeyHandler.isA_PRESSED() || KeyHandler.isUP_PRESSED() && KeyHandler.isLEFT_PRESSED())     // Direction Up-Left
        {
            playerEntity.setDirection(Direction.UP_LEFT);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isW_PRESSED() && KeyHandler.isD_PRESSED() || KeyHandler.isUP_PRESSED() && KeyHandler.isRIGHT_PRESSED())     // Direction Up-Right
        {
            playerEntity.setDirection(Direction.UP_RIGHT);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isS_PRESSED() && KeyHandler.isA_PRESSED() || KeyHandler.isDOWN_PRESSED() && KeyHandler.isLEFT_PRESSED())     // Direction Down-Left
        {
            playerEntity.setDirection(Direction.DOWN_LEFT);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isS_PRESSED() && KeyHandler.isD_PRESSED() || KeyHandler.isDOWN_PRESSED() && KeyHandler.isRIGHT_PRESSED())     // Direction Down-Right
        {
            playerEntity.setDirection(Direction.DOWN_RIGHT);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isS_PRESSED() || KeyHandler.isDOWN_PRESSED())   // Direction Down
        {
            playerEntity.setDirection(Direction.DOWN);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isA_PRESSED() || KeyHandler.isLEFT_PRESSED())  // Direction Left
        {
            playerEntity.setDirection(Direction.LEFT);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isD_PRESSED() || KeyHandler.isRIGHT_PRESSED()) // Direction right
        {
            playerEntity.setDirection(Direction.RIGHT);
            playerEntity.setMoving(true);
        }
        else if (KeyHandler.isW_PRESSED() || KeyHandler.isUP_PRESSED())   // Direction up
        {
            playerEntity.setDirection(Direction.UP);
            playerEntity.setMoving(true);
        }
    }
}
