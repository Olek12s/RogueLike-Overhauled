package main.entity;

import main.utilities.DrawPriority;
import main.GameController;
import main.IDrawable;
import main.utilities.Hitbox;
import main.utilities.Position;
import main.camera.Camera;
import main.utilities.sprite.Sprite;
import main.utilities.sprite.SpriteSheet;
import main.world.map.TileID;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class EntityRenderer implements IDrawable
{
    private final Entity entity;
    private static HashMap<EntityID, SpriteSheet> entitySpriteSheets;
    private int spriteVariation;
    private int animationTick;

    public int getSpriteVariation() {return spriteVariation;}
    public void setSpriteVariation(int spriteVariation) {this.spriteVariation = spriteVariation;}
    public int getAnimationTick() {return animationTick;}
    public void setAnimationTick(int animationTick) {this.animationTick = animationTick;}
    public static SpriteSheet getSpriteSheetByID(EntityID id) {return entitySpriteSheets.get(id);}

    public Sprite getCurrentSprite()
    {
        return entitySpriteSheets.get(entity.getEntityID()).extractSprite(animationTick, spriteVariation);
    }

    public EntityRenderer(Entity entity)
    {
        this.entity = entity;
        GameController.addDrawable(this);

        if (entitySpriteSheets == null)
        {
            loadTextures();
        }
    }

    private static void loadTextures()
    {
        entitySpriteSheets = new HashMap<>();

        try
        {
            entitySpriteSheets.put(EntityID.PLAYER, new SpriteSheet(ImageIO.read(new File("resources/txt/entity/Player.png")), 22, 22));
            entitySpriteSheets.put(EntityID.MINI_SLIME, new SpriteSheet(ImageIO.read(new File("resources/txt/entity/MiniSlime.png")), 22, 22));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void drawHitbox(Graphics g2, Hitbox hitbox, Color color)
    {
        Rectangle rect = hitbox.getHitboxRect();
        // Convert world position + hitbox offset to screen coordinates
        int worldX = rect.x;
        int worldY = rect.y;
        Position topLeft = Camera.toScreenPosition(worldX, worldY);
        double scale = Camera.getScaleFactor();
        int w = (int) Math.ceil(rect.width * scale);
        int h = (int) Math.ceil(rect.height * scale);

        g2.setColor(color);
        g2.drawRect(topLeft.getX(), topLeft.getY(), w, h);
    }


    @Override
    public DrawPriority getDrawPriority()
    {
        return DrawPriority.Entity;
    }

    @Override
    public void draw(Graphics g2)
    {
        Position screenPosition = Camera.toScreenPosition(entity.getWorldPosition());
        Sprite sprite = getCurrentSprite();
        BufferedImage image = sprite.getImage();

        int drawSizeX = (int) Math.ceil(sprite.resolutionX * Camera.getScaleFactor());
        int drawSizeY = (int) Math.ceil(sprite.resolutionY * Camera.getScaleFactor());

        g2.drawImage(image, screenPosition.getX(), screenPosition.getY(), drawSizeX, drawSizeY, null);
        drawHitbox(g2, entity.getHitbox(), Color.ORANGE);
    }
}
