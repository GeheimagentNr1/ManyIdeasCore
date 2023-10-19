package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;


public record RawShapedDyedRecipe(
	Boolean shaped,
	Map<String, Ingredient> key,
	List<String> pattern,
	ItemStack result) {
	
	
}
