package main.gui.components;

import main.GameController;
import main.crafting.CraftingManager;
import main.crafting.CraftingRecipe;
import main.gui.Gui;
import main.gui.GuiRenderer;
import main.inventory.Inventory;
import main.item.Item;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class CraftingGui
{
    private static final int COLUMNS = 4;      // number of columns for crafting grid
    private static final int PADDING = 10;     // padding between slots

    public CraftingGui()
    {

    }

    public void renderCrafting(Graphics g)  // temporary solution
    {
        Graphics2D g2d = (Graphics2D) g.create();

        int screenWidth = GameController.getInstance().getWidth();
        int screenHeight = GameController.getInstance().getHeight();
        int slotSize = Gui.getSlotSize();

        // Fetch all recipes
        List<CraftingRecipe> allRecipes = CraftingManager.getInstance().getRecipes();

        // Separate craftable and non-craftable
        Inventory inv = GameController.getPlayer().getInventory();
        List<CraftingRecipe> craftable = new ArrayList<>();
        List<CraftingRecipe> notCraftable = new ArrayList<>();
        for (CraftingRecipe recipe : allRecipes)
        {
            if (inv.hasCraftMaterials(recipe.getRequiredMaterials())) craftable.add(recipe);
            else notCraftable.add(recipe);
        }

        // Combine list: craftable first
        List<CraftingRecipe> displayList = new ArrayList<>();
        displayList.addAll(craftable);
        displayList.addAll(notCraftable);

        // Calculate rows needed
        int total = displayList.size();
        int rows = (int) Math.ceil((double) total / COLUMNS);
        int gridWidth = COLUMNS * slotSize + (COLUMNS - 1) * PADDING;
        int gridHeight = rows * slotSize + (rows - 1) * PADDING;

        // Top-left of grid (centered in x, positioned at 1/3 height)
        int startX = (screenWidth - gridWidth) / 2;
        int startY = screenHeight / 3;

        // Render each recipe slot
        for (int i = 0; i < total; i++)
        {
            CraftingRecipe recipe = displayList.get(i);
            int col = i % COLUMNS;
            int row = i / COLUMNS;
            int x = startX + col * (slotSize + PADDING);
            int y = startY + row * (slotSize + PADDING);

            boolean canCraft = craftable.contains(recipe);
            // Highlight craftable items
            if (canCraft)
            {
                GuiRenderer.renderFrame(g2d, x, y, slotSize, slotSize, 3, 4, 2, 0.6f);
            }
            else
            {
                GuiRenderer.renderFrame(g2d, x, y, slotSize, slotSize, 1, 2, 1, 0.4f);
            }

            // Draw result item
            Item result = recipe.getResult();
            GuiRenderer.renderInventoryItem(g2d, result, x, y, true);

            // Draw required materials below slot
            int textY = y + slotSize + Gui.getScaledFontSize();
            g2d.setFont(Gui.getFont());
            g2d.setColor(Color.WHITE);
            int matX = x;
            for (Item mat : recipe.getRequiredMaterials().keySet())
            {
                int count = recipe.getRequiredMaterials().get(mat);
                // draw small icon
                int iconSize = slotSize / 3;
                GuiRenderer.renderInventoryItem(g2d, mat, matX, textY, true);
                // draw count
                String text = "x" + count;
                int strW = g2d.getFontMetrics().stringWidth(text);
                g2d.drawString(text, matX + iconSize - strW, textY + Gui.getScaledFontSize());
                matX += iconSize + 5;
            }
        }
        g2d.dispose();
    }
}
