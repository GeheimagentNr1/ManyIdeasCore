package de.geheimagentnr1.manyideascore.elements.blocks.table_saws;

import de.geheimagentnr1.manyideascore.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public abstract class TableSawRecipe extends SingleItemRecipe {
	
	
	protected TableSawRecipe( IRecipeType<? extends TableSawRecipe> recipeType,
		IRecipeSerializer<? extends TableSawRecipe> recipeSerializer, ResourceLocation id, String group,
		Ingredient _ingredient, ItemStack _result ) {
		
		super( recipeType, recipeSerializer, id, group, _ingredient, _result );
	}
	
	@Override
	public boolean matches( IInventory inv, @Nonnull World worldIn ) {
		
		return ingredient.test( inv.getStackInSlot( 0 ) );
	}
	
	@Nonnull
	@Override
	public abstract ItemStack getIcon();
}