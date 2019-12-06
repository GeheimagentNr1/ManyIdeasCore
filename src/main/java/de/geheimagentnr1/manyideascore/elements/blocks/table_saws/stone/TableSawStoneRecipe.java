package de.geheimagentnr1.manyideascore.elements.blocks.table_saws.stone;

import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


public class TableSawStoneRecipe extends TableSawRecipe {
	
	
	public final static String registry_name = "table_sawing_stone";
	
	public TableSawStoneRecipe( ResourceLocation id, String group, Ingredient _ingredient,
		ItemStack _result ) {
		
		super( RecipeTypes.TABLE_SAWING_STONE, RecipeSerializers.TABLE_SAWING_STONE, id, group,
			_ingredient, _result );
	}
	
	@Nonnull
	@Override
	public ItemStack getIcon() {
		
		return new ItemStack( ModBlocks.TABLE_SAW_STONE );
	}
}
