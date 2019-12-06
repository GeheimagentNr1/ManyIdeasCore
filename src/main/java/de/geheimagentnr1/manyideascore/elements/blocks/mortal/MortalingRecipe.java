package de.geheimagentnr1.manyideascore.elements.blocks.mortal;

import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideascore.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public class MortalingRecipe extends SingleItemRecipe {
	
	
	public final static String registry_name = "mortaling";
	
	public MortalingRecipe( ResourceLocation _id, String _group, Ingredient _ingredient, ItemStack _result ) {
		
		super( RecipeTypes.MORTALING, RecipeSerializers.MORTALING, _id, _group, _ingredient, _result );
	}
	
	@Override
	public boolean matches( IInventory inv, @Nonnull World worldIn ) {
		
		return ingredient.test( inv.getStackInSlot( 0 ) );
	}
	
	@Nonnull
	@Override
	public ItemStack getIcon() {
		
		return new ItemStack( ModBlocks.MORTAL );
	}
}
