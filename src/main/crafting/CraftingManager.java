package main.crafting;

import java.util.ArrayList;
import java.util.List;

public class CraftingManager
{
    private static CraftingManager instance;
    private final List<CraftingRecipe> recipes;

    private CraftingManager() {
        this.recipes = new ArrayList<>();
    }

    public static synchronized CraftingManager getInstance() {
        if (instance == null) {
            instance = new CraftingManager();
        }
        return instance;
    }

    /**
     * Registers a new recipe. Call this at game initialization.
     */
    public void registerRecipe(CraftingRecipe recipe) {
        recipes.add(recipe);
    }

    /**
     * Retrieves all registered recipes.
     */
    public List<CraftingRecipe> getRecipes() {
        return recipes;
    }
}
