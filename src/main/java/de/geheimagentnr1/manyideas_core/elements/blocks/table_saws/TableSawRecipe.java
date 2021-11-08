package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;


public abstract class TableSawRecipe extends SingleItemRecipe {
	
	
	protected TableSawRecipe(
		RecipeType<? extends TableSawRecipe> recipeType,
		RecipeSerializer<? extends TableSawRecipe> recipeSerializer,
		ResourceLocation _id,
		String _group,
		Ingredient _ingredient,
		ItemStack _result ) {
		
		super( recipeType, recipeSerializer, _id, _group, _ingredient, _result );
	}
	
	@Override
	public boolean matches( @Nonnull Container inv, @Nonnull Level level ) {
		
		return ingredient.test( inv.getItem( 0 ) );
	}
	
	@Nonnull
	@Override
	public abstract ItemStack getToastSymbol();
}