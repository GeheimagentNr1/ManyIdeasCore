package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;


public interface ISingleItemRecipeFactory<T extends SingleItemRecipe> {
	
	
	default T create(
		@NotNull String group,
		@NotNull Ingredient ingredient,
		@NotNull Item resultItem,
		@NotNull int resultItemCount) {
		
		return create( group, ingredient, new ItemStackTemplate( resultItem, resultItemCount ) );
	}
	
	T create(
		@NotNull String group,
		@NotNull Ingredient ingredient,
		@NotNull ItemStackTemplate result );
}
