package de.geheimagentnr1.manyideas_core.integrations.jei.item_subtypes;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


public class DyeBlockItemSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
	
	
	@NotNull
	@Override
	public String apply( @NotNull ItemStack ingredient, @NotNull UidContext context ) {
		
		return DyeBlockHelper.getColorName( ingredient );
	}
}
