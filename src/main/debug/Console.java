package main.debug;

import main.GameController;
import main.IUpdatable;
import main.camera.Camera;
import main.utilities.Position;

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
            System.out.println("Hitbox position: " + hitboxPosition);
            System.out.println("Render: " + Camera.getRenderDistance());
        }
        catch (NullPointerException ex)
        {

        }
    }
}
