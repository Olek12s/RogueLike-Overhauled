package main.entity.player;

import main.GameController;
import main.Gamestate;
import main.gui.Gui;
import main.inventory.Inventory;
import main.inventory.Slot;
import main.inventory.SlotType;
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

import static main.inventory.SlotType.beltSlot;

public class PlayerUpdater extends EntityUpdater
{
    Player playerEntity;
    private boolean wasMouseClicked = false;


    public PlayerUpdater(Player entity)
    {
        super(entity);
        this.playerEntity = entity;
    }

    @Override
    public void update()
    {
        super.update();

        MouseHandler mh = GameController.getMouseHandler();
        if (mh.isLeftButtonReleased())
        {
            wasMouseClicked = false;
        }

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
        if (held == null) return;

        if (!GameController.getGameStateController().isInState(Gamestate.INVENTORY)) // on inventory close
        {
            dropHeldItemAtRandomNearby();
            return;
        }

        if (!MouseHandler.isLeftButtonClicked() || wasMouseClicked)    // no click happened
        {
            return;
        }
        wasMouseClicked = true;
        System.err.println("click");

        Position mousePos = MouseHandler.getMousePosition();
        Slot clickedSlot = Gui.getClickedSlot(mousePos);

        if (clickedSlot == null)    // if clicked away from clickable slots
        {
            dropHeldItemAtRandomNearby();
        }
        else
        {
          //  handleClickOnSlot(mousePos);
        }
    }

    private void handleClickOnSlot(Position clickPos)
    {
        Inventory inv = playerEntity.getInventory();
        Item held = playerEntity.getHeldItem();
        if (held == null) return;

        // Znajdź slot pod kursorem
        Slot clickedSlot = Gui.getClickedSlot(clickPos);
        if (clickedSlot == null) return;

        SlotType type = clickedSlot.getSlotType();

        switch (type) {
            case mainInvSlot -> {
                int x = clickedSlot.getColNum();
                int y = clickedSlot.getRowNum();
                Item inSlot = clickedSlot.getStoredItem();

                if (inSlot == null) {
                    // Wkładamy held do pustego slotu
                    inv.addItem(held, x, y);
                    playerEntity.setHeldItem(null);
                } else {
                    // Swap: held <-> inSlot
                    // 1. Włóż held na miejsce inSlot
                    inv.removeItemFromMainInv(x, y);
                    inv.addItem(held, x, y);
                    held.setInventoryPosition(new Position(x, y));

                    // 2. Podnieś poprzedni item
                    playerEntity.setHeldItem(inSlot);
                    inSlot.setInventoryPosition(null);

                    // Usuń stary item z listy i dodaj nowy
                    inv.getMainInventoryItemList().remove(inSlot);
                    inv.getMainInventoryItemList().add(held);
                }
            }
            case beltSlot -> {
                int idx = clickedSlot.getColNum();
                Item inSlot = clickedSlot.getStoredItem();

                if (inSlot == null) {
                    // Wkładamy held na pusty pasek
                    clickedSlot.setStoredItem(held);
                    playerEntity.setHeldItem(null);
                } else {
                    // Swap: held <-> inSlot
                    clickedSlot.setStoredItem(held);
                    playerEntity.setHeldItem(inSlot);
                }
            }
            default -> {
                // Inne sloty – zostawiamy bez zmian lub dodajemy kolejne case’y
            }
        }
    }

    private void dropHeldItemAtRandomNearby()
    {
        Position center = playerEntity.getHitbox().getCenterWorldPosition();
        int halfW = playerEntity.getHitbox().getWidth() / 2;
        int halfH = playerEntity.getHitbox().getHeight() / 2;

        int dx = (int) ((Math.random() * 2 - 1) * halfW);
        int dy = (int) ((Math.random() * 2 - 1) * halfH);
        Position dropPos = new Position(center.getX() + dx, center.getY() + dy);

        playerEntity.getHeldItem().dropOnGround(dropPos);
        playerEntity.setHeldItem(null);
    }
}
