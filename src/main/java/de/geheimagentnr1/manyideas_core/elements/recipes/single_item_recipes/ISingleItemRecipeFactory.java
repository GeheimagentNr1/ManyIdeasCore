package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;


@FunctionalInterface
public interface ISingleItemRecipeFactory<T extends SingleItemRecipe> {
	
	
	T create( ResourceLocation recipeId, String group, Ingredient ingredient, ItemStack result );
}
