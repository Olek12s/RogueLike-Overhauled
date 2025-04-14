package main.world.worldGeneration.preview;

import main.world.map.MapID;
import main.world.worldGeneration.IMapGenerationStrategy;
import main.world.worldGeneration.MapGenerator;
import main.world.worldGeneration.strategies.CaveGenerationStrategy;
import main.world.worldGeneration.strategies.DeepCaveGenerationStrategy;
import main.world.worldGeneration.strategies.SurfaceGenerationStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Preview extends JPanel
{
    private short[][] map;
    private BufferedImage image;
    private static int WIDTH;
    private static int HEIGHT;

    public Preview(short[][] map)
    {
        this.map = map;
        updateImage();
        setPreferredSize(new Dimension(map.length, map[0].length));
    }

    private void updateImage()
    {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < WIDTH; x++)
        {
            for (int y = 0; y < HEIGHT; y++)
            {
                int value = map[x][y] & 0xFF;
                int rgb = (value << 16) | (value << 8) | value;
                image.setRGB(x, y, rgb);
            }
        }
        repaint();
    }

    public void regenerateMap(IMapGenerationStrategy strategy)
    {
        long start = System.nanoTime();

        //MapGenerator generator = new MapGenerator(strategy);
        map = MapGenerator.generateMap(WIDTH, HEIGHT, strategy);
        MapGenerator.setSeed(System.currentTimeMillis());

        long end = System.nanoTime();

        double elapsedMillis = (end - start) / 1_000_000.0;
        double elapsedSeconds = elapsedMillis / 1000.0;

        System.out.printf("seconds: %.2f milliseconds: %.0f%n", elapsedSeconds, elapsedMillis);

        updateImage();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args)
    {
        int s = 9;  // theoretical minimum: 8
        // 8 - 256+1   -
        // 9 - 512+1   - preffered for caves
        // 10 - 1024+1 - preffered for surface
        // 11 - 2048+1
        int size = (int)Math.pow(2, s)+1;
        WIDTH = size;
        HEIGHT = size;

        SwingUtilities.invokeLater(() -> {
            IMapGenerationStrategy initialStrategy = new CaveGenerationStrategy();
            Preview previewPanel = new Preview(MapGenerator.generateMap(WIDTH, HEIGHT, initialStrategy));

            // UI frame
            JFrame frame = new JFrame("Map Preview");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Buttons panel
            JPanel buttonsPanel = new JPanel(new FlowLayout());
            JButton caveButton = new JButton("Cave");
            JButton deepCaveButton = new JButton("Deep Cave");
            JButton surfaceButton = new JButton("Surface");

            // Actions
            caveButton.addActionListener(e -> previewPanel.regenerateMap(new CaveGenerationStrategy()));
            deepCaveButton.addActionListener(e -> previewPanel.regenerateMap(new DeepCaveGenerationStrategy()));
            surfaceButton.addActionListener(e -> previewPanel.regenerateMap(new SurfaceGenerationStrategy()));

            buttonsPanel.add(caveButton);
            buttonsPanel.add(deepCaveButton);
            buttonsPanel.add(surfaceButton);

            // Layout
            frame.setLayout(new BorderLayout());
            frame.add(previewPanel, BorderLayout.CENTER);
            frame.add(buttonsPanel, BorderLayout.SOUTH);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
