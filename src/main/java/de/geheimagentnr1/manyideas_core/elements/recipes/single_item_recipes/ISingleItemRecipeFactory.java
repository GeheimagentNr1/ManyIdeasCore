package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;


@FunctionalInterface
public interface ISingleItemRecipeFactory<T extends SingleItemRecipe> {
	
	
	T create( ResourceLocation recipeId, String group, Ingredient ingredient, ItemStack result );
}
