package main.debug;

import main.GameController;
import main.IUpdatable;

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
        long updateTime = GameController.getInstance().getUpdateTime();
        long renderTime = GameController.getInstance().getRenderTime();

        System.out.println("Update time: " + updateTime / 1_000_000.0 + " ms | Render time: " + renderTime / 1_000_000.0 + " ms");
    }
}
