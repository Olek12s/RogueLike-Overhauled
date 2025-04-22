package main.utilities.sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet
{
    private static final int SPRITE_PADDING = 1;
    private static final int SPRITE_OFFSET = 1;

    private BufferedImage spriteSheetImage;
    private Sprite[][] sprites;
    private int textureWidth;
    private int textureHeight;
    private int animationTicks;
    private int textureVarations;

    public BufferedImage getSpriteSheetImage() {return spriteSheetImage;}
    public int getTextureWidth() {return textureWidth;}
    public int getTextureHeight() {return textureHeight;}
    public int getAnimationTicks() {return animationTicks;}
    public int getTextureVarations() {return textureVarations;}


    public SpriteSheet(BufferedImage spriteSheetImage, int textureWidth, int textureHeight)
    {
        this.spriteSheetImage = spriteSheetImage;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.animationTicks = countAnimationTicks();
        this.textureVarations = countTextureVariations();

        // Cache all individual sprites
        sprites = new Sprite[animationTicks][textureVarations];
        for (int t = 0; t < animationTicks; t++)
        {
            for (int v = 0; v < textureVarations; v++)
            {
                int startX = SPRITE_OFFSET + t * (textureWidth + SPRITE_PADDING);
                int startY = SPRITE_OFFSET + v * (textureHeight + SPRITE_PADDING);
                BufferedImage sub = spriteSheetImage.getSubimage(startX, startY, textureWidth, textureHeight);
                sprites[t][v] = new Sprite(sub);
            }
        }
    }

    private int countAnimationTicks()
    {
        int sheetWidth = spriteSheetImage.getWidth();
        int spritePlusPadding = textureWidth + SPRITE_PADDING;
        int count = 0;

        for (int x = SPRITE_OFFSET; x + textureWidth <= sheetWidth; x += spritePlusPadding)
        {
            if (isSpritePresent(x, SPRITE_OFFSET))
            {
                count++;
            }
            else
            {
                break;
            }
        }
        return count;
    }

    private int countTextureVariations()
    {
        int sheetHeight = spriteSheetImage.getHeight();
        int availableHeight = sheetHeight - SPRITE_OFFSET;
        int spritePlusPadding = textureHeight + SPRITE_PADDING;
        int count = 0;

        for (int y = SPRITE_OFFSET; y + textureHeight <= sheetHeight; y += spritePlusPadding) {
            if (isSpritePresent(SPRITE_OFFSET, y))
            {
                count++;
            }
            else
            {
                break;
            }
        }

        return count;
    }

    private boolean isSpritePresent(int startX, int startY)
    {
        int endX = startX + textureWidth;
        int endY = startY + textureHeight;

        for (int x = startX; x < endX; x++)
        {
            for (int y = startY; y < endY; y++)
            {
                int pixel = spriteSheetImage.getRGB(x, y);
                if ((pixel >> 24) != 0x00)  // Check for non-transparent pixel
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Sprite extractSprite(int tick, int variation)
    {
        return sprites[tick][variation];
    }

    public Sprite extractFirst()
    {
        return sprites[0][0];
    }

    public static void main(String[] args)
    {
        //String path = "resources/test32x64.png";
        //String path = "resources/DefaultTile.png";
        String path = "resources/MiniSlime.png";
        int texW = 22;
        int texH = 22;


        SwingUtilities.invokeLater(() -> {
            try {
                BufferedImage sheetImg = ImageIO.read(new File(path));
                SpriteSheet sheet = new SpriteSheet(sheetImg, texW, texH);

                JFrame frame = new JFrame("SpriteSheet Tester");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                JLabel imgLabel = new JLabel(new ImageIcon(sheet.extractSprite(0, 0).getImage()));
                imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
                frame.add(imgLabel, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 5, 5));
                JButton prevTick = new JButton("Previous Tick");
                JButton nextTick = new JButton("Next Tick");
                JButton prevVar = new JButton("Previous Variation");
                JButton nextVar = new JButton("Next Variation");
                buttonPanel.add(prevVar);
                buttonPanel.add(nextVar);
                buttonPanel.add(prevTick);
                buttonPanel.add(nextTick);
                frame.add(buttonPanel, BorderLayout.SOUTH);

                final int[] current = {0, 0}; // [tick, variation]
                ActionListener updateImage = e -> {
                    imgLabel.setIcon(new ImageIcon(sheet.extractSprite(current[0], current[1]).getImage()));
                    prevTick.setEnabled(current[0] > 0);
                    nextTick.setEnabled(current[0] < sheet.getAnimationTicks() - 1);
                    prevVar.setEnabled(current[1] > 0);
                    nextVar.setEnabled(current[1] < sheet.getTextureVarations() - 1);
                };

                prevTick.addActionListener(e -> {
                    current[0]--;
                    updateImage.actionPerformed(null);
                });
                nextTick.addActionListener(e -> {
                    current[0]++;
                    updateImage.actionPerformed(null);
                });
                prevVar.addActionListener(e -> {
                    current[1]--;
                    updateImage.actionPerformed(null);
                });
                nextVar.addActionListener(e -> {
                    current[1]++;
                    updateImage.actionPerformed(null);
                });

                // Initial button state
                updateImage.actionPerformed(null);

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(2);
            }
        });
    }
}
