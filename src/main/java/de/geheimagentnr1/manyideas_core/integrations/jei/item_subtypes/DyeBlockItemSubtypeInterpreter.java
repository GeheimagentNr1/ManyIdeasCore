package de.geheimagentnr1.manyideas_core.integrations.jei.item_subtypes;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.item.ItemStack;


public class DyeBlockItemSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack> {
	
	
	@Override
	public String apply( ItemStack ingredient, UidContext context ) {
		
		return DyeBlockHelper.getColorName( ingredient );
	}
}
