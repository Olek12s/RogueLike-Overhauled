package main.world.map;

import main.GameController;
import main.IDrawable;
import main.camera.Camera;
import main.utilities.DrawPriority;
import main.utilities.Position;
import main.utilities.sprite.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapRenderer implements IDrawable
{
    private Map map;
    private static BufferedImage defaultTileTexture;

    public MapRenderer(Map map)
    {
        this.map = map;
        loadDefaultTileTexture();
        GameController.addDrawable(this);
    }

    @Override
    public DrawPriority getDrawPriority() {
        return DrawPriority.Map;
    }

    private void loadDefaultTileTexture()
    {
        try
        {
           // defaultTileTexture = ImageIO.read(new File("resources/DefaultTile.png"));
            BufferedImage defaultTileTextureSpriteSheett = ImageIO.read(new File("resources/DefaultTile.png"));
            SpriteSheet defaultTileTextureSpriteSheet = new SpriteSheet(defaultTileTextureSpriteSheett, 64, 64*3);
            defaultTileTexture = defaultTileTextureSpriteSheet.extractSprite(0, 0);

        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("Failed to load default tile texture: " + e.getMessage());
            defaultTileTexture = new BufferedImage(
                    Tile.getTileSize(), Tile.getTileSize(), BufferedImage.TYPE_INT_ARGB
            );
        }
    }

    @Override
    public void draw(Graphics g2) {
        Chunk[][] chunks = map.getChunks();
        for (Chunk[] chunkRow : chunks) {
            for (Chunk chunk : chunkRow) {
                if (chunk == null) continue;
                Tile[][] tiles = chunk.getTiles();
                for (Tile[] tileRow : tiles) {
                    for (Tile tile : tileRow) {
                        Position worldPos = tile.getTileWorldPosition();
                        Position screenPos = Camera.toScreenPosition(worldPos);
                        //System.out.println("Drawing tile at world: " + worldPos + " -> screen: " + screenPos);
                        g2.drawImage(
                                defaultTileTexture,
                                screenPos.getX(), screenPos.getY(),
                                Tile.getTileSize(), Tile.getTileSize(),
                                null
                        );
                    }
                }
            }
        }
    }
}
