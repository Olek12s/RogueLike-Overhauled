package main;

import main.camera.Camera;
import main.debug.Console;
import main.entity.Entity;
import main.entity.player.Player;
import main.userInput.KeyHandler;
import main.userInput.MouseHandler;
import main.world.map.MapManager;
import main.world.worldGeneration.MapGenerator;

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

    private static ArrayList<IDrawable> drawables;
    private static ArrayList<IUpdatable> updatables;

                            //                       //
                            //  GETTERS AND SETTERS  //
                            //                       //
    public int getTargetDrawFrame() {return targetDrawFrame;}
    public int getTargetLogicFrame() {return targetLogicFrame;}
    public long getUpdateTime() {return updateTime;}
    public long getRenderTime() {return renderTime;}
    public ArrayList<IDrawable> getDrawables() {return drawables;}
    public ArrayList<IUpdatable> getUpdatables() {return updatables;}
    public static Entity getPlayer() {return player;}
    //public static MapGenerator getMapGenerator() {return mapGenerator;}
    public static MapManager getMapManager() {return mapManager;}

    public static void addUpdatable(IUpdatable updatable) {updatables.add(updatable);}
    public static void addDrawable(IDrawable drawable) {drawables.add(drawable);}




                            //                       //
                            //  CLASS INSTANCES      //
                            //                       //

    private static Console debugConsole;
    private static Entity player;
    private static MouseHandler mouseHandler;
    private static KeyHandler keyHandler;
    private static Camera camera;
    //private static MapGenerator mapGenerator;
    private static MapManager mapManager;


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
            initializeThread();
            initializeUserInput();

            debugConsole = new Console();
            player = new Player();
            camera = new Camera();
            //mapGenerator = new MapGenerator();
            mapManager = new MapManager();
        }
        return instance;
    }

    private static void initializeUserInput() {
        mouseHandler = new MouseHandler();
        keyHandler = new KeyHandler();

        instance.addMouseListener(mouseHandler);
        instance.addMouseMotionListener(mouseHandler);
        instance.addMouseWheelListener(mouseHandler);
        instance.addKeyListener(keyHandler);
    }
    private static void initializeThread() {
        instance.setBackground(Color.BLACK);
        instance.setDoubleBuffered(true);
        instance.setFocusable(true);

        mainThread = new Thread(GameController.getInstance());
        mainThread.start();
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

        drawables.sort((e1, e2) -> Integer.compare(e1.getDrawPriority().value, e2.getDrawPriority().value));
        for (IDrawable drawable : drawables)
        {
            drawable.draw(g2);
        }
        g2.dispose();
        long end = System.nanoTime();
        renderTime = end-start;
    }
}
