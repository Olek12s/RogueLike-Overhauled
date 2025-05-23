package main.crafting;

import main.item.Item;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CraftingRecipe
{
    private final Item result;
    private final Map<Item, Integer> requiredMaterials;

    public CraftingRecipe(Item result, Map<Item, Integer> requiredMaterials)
    {
        this.result = result;
        this.requiredMaterials = new LinkedHashMap<>(requiredMaterials);
    }

    public Item getResult()
    {
        return result;
    }

    public Map<Item, Integer> getRequiredMaterials()
    {
        return Collections.unmodifiableMap(requiredMaterials);
    }
}
