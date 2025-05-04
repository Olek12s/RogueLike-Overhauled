package main.debug;

import main.GameController;
import main.IUpdatable;
import main.camera.Camera;
import main.entity.Entity;
import main.entity.player.Player;
import main.item.Item;
import main.utilities.Position;
import main.world.map.MapManager;

public class Console implements IUpdatable
{
    private long lastCyclicPrintTime = 0;

    public Console()
    {
        GameController.getInstance().getUpdatables().add(this);
    }

    @Override
    public void update()
    {
        long now = System.currentTimeMillis();

        if (now - lastCyclicPrintTime >= 1000)
        {
            cyclicPrint();
            lastCyclicPrintTime = now;
        }
    }

    private void cyclicPrint()
    {
        try
        {
            long updateTime = GameController.getInstance().getUpdateTime();
            long renderTime = GameController.getInstance().getRenderTime();
            Position playerPosition = GameController.getPlayer().getWorldPosition();
            Position hitboxPosition = GameController.getPlayer().getHitbox().getWorldPosition();

            System.out.print("Update time: " + updateTime / 1_000_000.0 + " ms | Render time: " + renderTime / 1_000_000.0 + " ms | ");
            System.out.print("World Position: " + playerPosition + " | ");
            System.out.print("Hitbox position: " + hitboxPosition + " | ");
            System.out.print("Render: " + Camera.getRenderDistance() + " | ");
            Item held = ((Player) GameController.getPlayer()).getHeldItem();
            if (held != null)
            {
                System.out.println("Held: " + held.getStatistics().getItemName() + " | ");
            }
            else
            {
                System.out.println("Held: nothing | ");
            }
            //System.out.println("Tile: " + MapManager.getCurrentMap().getTile(playerPosition));
        }
        catch (NullPointerException ex)
        {

        }
    }
}
