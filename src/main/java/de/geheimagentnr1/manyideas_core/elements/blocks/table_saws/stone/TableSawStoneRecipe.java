package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;


public class TableSawStoneRecipe extends TableSawRecipe {
	
	
	public static final String registry_name = "table_sawing_stone";
	
	public TableSawStoneRecipe( ResourceLocation id, String group, Ingredient _ingredient, ItemStack _result ) {
		
		super( RecipeTypes.TABLE_SAWING_STONE, RecipeSerializers.TABLE_SAWING_STONE, id, group, _ingredient, _result );
	}
	
	@Nonnull
	@Override
	public ItemStack getToastSymbol() {
		
		return new ItemStack( ModBlocks.TABLE_SAW_STONE );
	}
}
