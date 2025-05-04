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
        checkPickUpClickedItemFromSlot();
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

        Position mousePos = MouseHandler.getMousePosition();
        Slot clickedSlot = Gui.getClickedSlot(mousePos);

        if (clickedSlot == null)    // if clicked away from clickable slots
        {
            dropHeldItemAtRandomNearby();
        }
        else
        {
             handleClickOnSlot(mousePos);
        }
    }

    private void handleClickOnSlot(Position clickPos)
    {
        Inventory inv = playerEntity.getInventory();
        Item held = playerEntity.getHeldItem();
        if (held == null) return;

        Slot clickedSlot = Gui.getClickedSlot(clickPos);
        if (clickedSlot == null) return;

        SlotType type = clickedSlot.getSlotType();

        switch (type) {
            case mainInvSlot -> {
                int x = clickedSlot.getRowNum();
                int y = clickedSlot.getColNum();
                Item inSlot = clickedSlot.getStoredItem();

                if (inSlot == null)
                {
                    if(inv.addItem(held, x, y)) playerEntity.setHeldItem(null);
                }
                else {

                    inv.removeItemFromMainInv(x, y);
                    inv.addItem(held, x, y);
                    held.setInventoryPosition(new Position(x, y));


                    playerEntity.setHeldItem(inSlot);
                    inSlot.setInventoryPosition(null);


                    inv.getMainInventoryItemList().remove(inSlot);
                    inv.getMainInventoryItemList().add(held);
                }
            }
            case beltSlot -> {
                int idx = clickedSlot.getColNum();
                Item inSlot = clickedSlot.getStoredItem();

                if (inSlot == null) {

                    clickedSlot.setStoredItem(held);
                    playerEntity.setHeldItem(null);
                } else {

                    clickedSlot.setStoredItem(held);
                    playerEntity.setHeldItem(inSlot);
                }
            }
            default -> {

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

    private void checkPickUpClickedItemFromSlot()
    {
        Player player = (Player)GameController.getPlayer();

        if (player.getHeldItem() != null) return;
        Slot clickedSlot = getClickedInventorySlot();
        if (clickedSlot == null || clickedSlot.getStoredItem() == null) return;

        System.out.print(clickedSlot.getStoredItem().getStatistics().getItemName());
        player.setHeldItem(clickedSlot.getStoredItem());
        removeItemFromSlot(clickedSlot);
    }

    private Slot getClickedInventorySlot()
    {
        MouseHandler mh = GameController.getMouseHandler();
        if (!mh.isLeftButtonClicked() || wasMouseClicked) {return null;}
        wasMouseClicked = true;
        if (!GameController.getGameStateController().isInState(Gamestate.INVENTORY)) {return null;}

        Position clickPos = mh.getMousePosition();
        return Gui.getClickedSlot(clickPos);
    }

    private void removeItemFromSlot(Slot slot)
    {
        SlotType type = slot.getSlotType();
        Player player = (Player)GameController.getPlayer();

        switch (type)
        {
            case mainInvSlot -> player.getInventory().removeItemFromMainInv(slot.getRowNum(), slot.getColNum());
            case beltSlot     -> player.getInventory().removeItemFromBelt(slot.getRowNum());
            default           -> player.getInventory().removeItemFromEquipmentSlots(type);
        }
    }
}
