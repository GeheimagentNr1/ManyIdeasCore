package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;


public abstract class TableSawRecipe extends SingleItemRecipe {
	
	
	protected TableSawRecipe(
		@NotNull RecipeType<? extends TableSawRecipe> recipeType,
		@NotNull RecipeSerializer<? extends TableSawRecipe> recipeSerializer,
		@NotNull String _group,
		@NotNull Ingredient _ingredient,
		@NotNull ItemStack _result ) {
		
		super( recipeType, recipeSerializer, _group, _ingredient, _result );
	}
	
	@Override
	public boolean matches( @NotNull Container inv, @NotNull Level level ) {
		
		return ingredient.test( inv.getItem( 0 ) );
	}
	
	@NotNull
	@Override
	public abstract ItemStack getToastSymbol();
}