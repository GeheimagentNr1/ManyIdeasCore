package de.geheimagentnr1.manyideas_core.integrations.jei.item_subtypes;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


public class DyeBlockItemSubtypeInterpreter implements ISubtypeInterpreter<ItemStack> {
	
	
	@NotNull
	@Override
	public Object getSubtypeData( @NotNull ItemStack ingredient, @NotNull UidContext context ) {
		
		return DyeBlockHelper.getColor( ingredient );
	}
	
	@NotNull
	@Override
	public String getLegacyStringSubtypeInfo( @NotNull ItemStack ingredient, @NotNull UidContext context ) {
		
		return DyeBlockHelper.getColor( ingredient ).getSerializedName();
	}
}
