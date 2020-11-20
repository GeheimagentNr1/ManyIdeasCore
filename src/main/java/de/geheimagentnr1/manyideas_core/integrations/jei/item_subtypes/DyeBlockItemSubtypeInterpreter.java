package de.geheimagentnr1.manyideas_core.integrations.jei.item_subtypes;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import net.minecraft.item.ItemStack;


public class DyeBlockItemSubtypeInterpreter implements ISubtypeInterpreter {
	
	
	@Override
	public String apply( ItemStack itemStack ) {
		
		return DyeBlockHelper.getColorName( itemStack );
	}
}
