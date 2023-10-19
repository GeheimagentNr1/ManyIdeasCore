package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;


public class GrindingRecipe extends SingleItemRecipe {
	
	
	@NotNull
	public static final String registry_name = "grinding";
	
	public GrindingRecipe(
		@NotNull String _group,
		@NotNull Ingredient _ingredient,
		@NotNull ItemStack _result ) {
		
		super(
			ModRecipeTypesRegisterFactory.GRINDING,
			ModRecipeSerializersRegisterFactory.GRINDING,
			_group,
			_ingredient,
			_result
		);
	}
	
	@Override
	public boolean matches( @NotNull Container inv, @NotNull Level level ) {
		
		return ingredient.test( inv.getItem( 0 ) );
	}
	
	@NotNull
	@Override
	public ItemStack getToastSymbol() {
		
		return new ItemStack( ModBlocksRegisterFactory.MORTAR );
	}
}
