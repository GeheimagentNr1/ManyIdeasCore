package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;


@FunctionalInterface
public interface ISingleItemRecipeFactory<T extends SingleItemRecipe> {
	
	
	T create(
		@NotNull ResourceLocation recipeId,
		@NotNull String group,
		@NotNull Ingredient ingredient,
		@NotNull ItemStack result );
}
