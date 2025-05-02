package main.world.map;

import main.GameController;
import main.IDrawable;
import main.camera.Camera;
import main.item.Item;
import main.utilities.DrawPriority;
import main.utilities.Position;
import main.world.tile.Tile;
import java.util.List;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapRenderer implements IDrawable
{
    private Map map;

    public MapRenderer(Map map)
    {
        this.map = map;
        GameController.addDrawable(this);
    }

    @Override
    public DrawPriority getDrawPriority() {
        return DrawPriority.Map;
    }

    @Override
    public void draw(Graphics g2) {
        Chunk[][] chunks = map.getChunks();
        if (chunks == null || chunks.length == 0) return;

        int chunkCountX = chunks.length;
        int chunkCountY = chunks[0].length;
        int chunkSize = Chunk.getChunkSize();
        int tileSize = Tile.getTileSize();
        Position camPos = GameController.getInstance().getCamera().getPositionToFocus();
        Chunk cameraChunk = map.getChunk(camPos);
        int camChunkX = cameraChunk.getxIndex();
        int camChunkY = cameraChunk.getyIndex();


        int renderDistance = Camera.getRenderDistance();
        int startChunkX = Math.max(0, camChunkX - renderDistance);
        int startChunkY = Math.max(0, camChunkY - renderDistance);
        int endChunkX = Math.min(chunkCountX, camChunkX + renderDistance + 1);
        int endChunkY = Math.min(chunkCountY, camChunkY + renderDistance + 1);

        // draw only chunks within range
        for (int chunkX = startChunkX; chunkX < endChunkX; chunkX++)
        {
            for (int chunkY = startChunkY; chunkY < endChunkY; chunkY++)
            {
                Chunk chunk = chunks[chunkX][chunkY];
                if (chunk == null) continue;

                Tile[][] tiles = chunk.getTiles();
                for (int x = 0; x < chunkSize; x++)
                {
                    for (int y = 0; y < chunkSize; y++)
                    {
                        Tile tile = tiles[x][y];
                        Position worldPos = tile.getTileWorldPosition();
                        Position screenPos = Camera.toScreenPosition(worldPos);
                        int drawSize = (int) Math.ceil(tileSize * Camera.getScaleFactor());
                        g2.drawImage(tile.getSpriteImage(), screenPos.getX(), screenPos.getY(), drawSize, drawSize, null);
                    }
                }
                drawChunkBoundary(g2, chunk);
                drawItemsOnGround(g2, chunk);
            }
        }
    }

    private void drawChunkBoundary(Graphics g2, Chunk chunk)
    {
        int chunkPixelSize = Chunk.getChunkSize() * Tile.getTileSize();
        final int boundaryThickness= 1;

        Position chunkWorldPos = chunk.getChunkWorldPosition();
        Position screenPos = Camera.toScreenPosition(chunkWorldPos);

        double scale = Camera.getScaleFactor();
        int scaledWidth  = (int) (chunkPixelSize * scale);
        int scaledHeight = (int) (chunkPixelSize * scale);
        float stroke = (float) (boundaryThickness * scale);

        Graphics2D g2d = (Graphics2D) g2;
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(stroke));
        g2d.drawRect(screenPos.getX(), screenPos.getY(), scaledWidth, scaledHeight);
    }

    private void drawItemsOnGround(Graphics g2, Chunk chunk)
    {
        List<Item> items = chunk.getItems();
        if (items == null || items.isEmpty()) return;

        double scale = Camera.getScaleFactor();

        for (Item item : items)
        {
            Position wp = item.getWorldPosition();
            if (wp == null)
            {
                continue;
            }

            Position sp = Camera.toScreenPosition(wp);
            BufferedImage img = item.getSprite().getImage();
            int w = (int) Math.ceil(img.getWidth()  * scale);
            int h = (int) Math.ceil(img.getHeight() * scale);

            g2.drawImage(img, sp.getX(), sp.getY(), w, h, null);
        }
    }
}
