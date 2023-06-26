package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;


public class TableSawIronRecipe extends TableSawRecipe {
	
	
	@NotNull
	public static final String registry_name = "table_sawing_iron";
	
	public TableSawIronRecipe(
		@NotNull ResourceLocation _id,
		@NotNull String _group,
		@NotNull Ingredient _ingredient,
		@NotNull ItemStack _result ) {
		
		super(
			ModRecipeTypesRegisterFactory.TABLE_SAWING_IRON,
			ModRecipeSerializersRegisterFactory.TABLE_SAWING_IRON,
			_id,
			_group,
			_ingredient,
			_result
		);
	}
	
	@NotNull
	@Override
	public ItemStack getToastSymbol() {
		
		return new ItemStack( ModBlocksRegisterFactory.TABLE_SAW_IRON );
	}
}
