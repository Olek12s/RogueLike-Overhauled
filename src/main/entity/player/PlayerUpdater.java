package main.entity.player;

import main.GameController;
import main.Gamestate;
import main.gui.Gui;
import main.inventory.Slot;
import main.item.Item;
import main.userInput.MouseHandler;
import main.utilities.Direction;
import main.utilities.Position;
import main.entity.Entity;
import main.entity.EntityUpdater;
import main.userInput.KeyHandler;
import main.world.map.Chunk;
import main.world.map.Map;
import main.world.map.MapManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerUpdater extends EntityUpdater
{
    Player playerEntity;
    public PlayerUpdater(Player entity)
    {
        super(entity);
        this.playerEntity = entity;
    }

    @Override
    public void update()
    {
        super.update();
        updatePlayerDirection();
        checkCrouch();

        if (KeyHandler.isF_PRESSED()) pickUpItemFromGround();
        checkDropHeldItemOnGround();
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

    private void checkCrouch()
    {
        if (KeyHandler.isCTRL_PRESSED())
        {
            playerEntity.setCrouching(true);
        }
        else playerEntity.setCrouching(false);
    }

    public void pickUpItemFromGround()
    {
        // collect items from current and neighboring chunks
        Chunk current = playerEntity.getCurrentChunk();
        Map map = MapManager.getCurrentMap();
        Set<Item> candidates = new HashSet<>(current.getItems());
        List<Chunk> neighbors = map.getChunkNeighborsDiagonals(current);
        for (Chunk c : neighbors)
        {
            candidates.addAll(c.getItems());
        }
        // attempt picking up items
        for (Item item : candidates)
        {
            if (playerEntity.getHitbox().intersects(item.getHitbox()))
            {
                if (playerEntity.getInventory().addItem(item))
                {
                    item.setInsideInventory();
                }
            }
        }
    }

    public void checkDropHeldItemOnGround()
    {
        Item held = playerEntity.getHeldItem();
        if (held == null) {return;}
        if (!GameController.getGameStateController().isInState(Gamestate.INVENTORY))
        if (!MouseHandler.isLeftButtonClicked()) return;

        System.out.print("attempt to drop");
        int mouseX = MouseHandler.getMousePosition().getX();
        int mouseY = MouseHandler.getMousePosition().getY();

        Slot clickedSlot = Gui.getClickedSlot(new Position(mouseX, mouseY));


        if (clickedSlot == null)
        {
            Position center = playerEntity.getHitbox().getCenterWorldPosition();
            int halfTileW = playerEntity.getHitbox().getWidth() / 2;
            int halfTileH = playerEntity.getHitbox().getHeight() / 2;
            int dx = (int) ((Math.random() * 2 - 1) * halfTileW);
            int dy = (int) ((Math.random() * 2 - 1) * halfTileH);
            Position dropPos = new Position(center.getX() + dx, center.getY() + dy);

            held.dropOnGround(dropPos);
            playerEntity.setHeldItem(null);

        }
    }
}
