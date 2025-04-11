package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameController extends JPanel implements Runnable
{
    private static Thread mainThread;
    private static GameController instance;
    private int targetDrawFrame =  75;
    private int targetLogicFrame = 60;
    private long updateTime;
    private long renderTime;

    private ArrayList<IDrawable> drawables;
    private ArrayList<IUpdatable> updatables;

                            //                       //
                            //  GETTERS AND SETTERS  //
                            //                       //
    public int getTargetDrawFrame() {return targetDrawFrame;}
    public int getTargetLogicFrame() {return targetLogicFrame;}
    public long getUpdateTime() {return updateTime;}
    public long getRenderTime() {return renderTime;}
    public ArrayList<IDrawable> getDrawables() {return drawables;}
    public ArrayList<IUpdatable> getUpdatables() {return updatables;}



                            //                       //
                            //  CLASS INSTANCES      //
                            //                       //

  private static Console debugConsole;

    private GameController()
    {
        drawables = new ArrayList<>();
        updatables = new ArrayList<>();
        mainThread = new Thread();
        mainThread.start();
    }

    public static GameController getInstance()
    {
        if (instance == null)
        {
            instance = new GameController();
            mainThread = new Thread(GameController.getInstance());
            mainThread.start();

            debugConsole = new Console();
        }
        return instance;
    }

    @Override
    public void run()       // MAIN LOOP
    {
        targetLogicFrame++;
        long logicInterval = 1000000000 / targetLogicFrame;   // logic ns
        long drawInterval = 1000000000 / targetDrawFrame;     // draw ns
        int maxLogicUpdatesPerFrame = Math.max(targetDrawFrame / targetLogicFrame, 1);

        long lastTime = System.nanoTime();
        long accumulator = 0;
        long maxAccumulator = logicInterval * maxLogicUpdatesPerFrame;
        while (mainThread != null)
        {

            long currentTime = System.nanoTime();
            long deltaT = currentTime - lastTime;
            lastTime = currentTime;

            accumulator += deltaT;

            if (accumulator > maxAccumulator)
            {
                accumulator = maxAccumulator;
            }

            int logicUpdates = 0;
            while (accumulator >= logicInterval && logicUpdates < maxLogicUpdatesPerFrame)
            {
                updateLogic();
                accumulator -= logicInterval;
                logicUpdates++;
            }
            repaint();
            try
            {
                long sleepTime = drawInterval - (System.nanoTime() - currentTime);
                if (sleepTime > 0)
                {
                    Thread.sleep(sleepTime / 1000000);  // ns -> ms
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void updateLogic()    // UPDATE LOGIC
    {
        long start = System.nanoTime();
        for (IUpdatable updatable : new ArrayList<>(updatables)) // copy of list for safe removing during iteration
        {
            updatable.update();
        }
        long end = System.nanoTime();
        updateTime = end-start;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        long start = System.nanoTime();
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        drawables.sort((e1, e2) -> Integer.compare(e1.getDrawPriority(), e2.getDrawPriority()));
        for (IDrawable drawable : drawables)
        {
            drawable.draw(g2);  // !!! BOTTLE NECK WARNING: DRAWABLES' SPRITES ARE SCALED EVERY ITERATION WHICH IS EXTREMELY CPU-CONSUMING !!!
        }
        g2.dispose();
        long end = System.nanoTime();
        renderTime = end-start;
    }
}
