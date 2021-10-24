package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public abstract class TableSawRecipe extends SingleItemRecipe {
	
	
	protected TableSawRecipe(
		IRecipeType<? extends TableSawRecipe> recipeType,
		IRecipeSerializer<? extends TableSawRecipe> recipeSerializer,
		ResourceLocation _id,
		String _group,
		Ingredient _ingredient,
		ItemStack _result ) {
		
		super( recipeType, recipeSerializer, _id, _group, _ingredient, _result );
	}
	
	@Override
	public boolean matches( IInventory inv, @Nonnull World worldIn ) {
		
		return ingredient.test( inv.getItem( 0 ) );
	}
	
	@Nonnull
	@Override
	public abstract ItemStack getToastSymbol();
}