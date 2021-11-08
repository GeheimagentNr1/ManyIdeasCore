package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;


public class GrindingRecipe extends SingleItemRecipe {
	
	
	public static final String registry_name = "grinding";
	
	public GrindingRecipe( ResourceLocation _id, String _group, Ingredient _ingredient, ItemStack _result ) {
		
		super( RecipeTypes.GRINDING, RecipeSerializers.GRINDING, _id, _group, _ingredient, _result );
	}
	
	@Override
	public boolean matches( Container inv, @Nonnull Level level ) {
		
		return ingredient.test( inv.getItem( 0 ) );
	}
	
	@Nonnull
	@Override
	public ItemStack getToastSymbol() {
		
		return new ItemStack( ModBlocks.MORTAR );
	}
}
