package main.crafting;

import java.util.ArrayList;
import java.util.List;

public class CraftingManager
{
    private static CraftingManager instance;
    private final List<CraftingRecipe> recipes;

    private CraftingManager()
    {
        this.recipes = new ArrayList<>();
    }

    public static CraftingManager getInstance()
    {
        if (instance == null)
        {
            instance = new CraftingManager();
        }
        return instance;
    }


    public void registerRecipe(CraftingRecipe recipe)
    {
        recipes.add(recipe);
    }


    public List<CraftingRecipe> getRecipes()
    {
        return recipes;
    }
}
