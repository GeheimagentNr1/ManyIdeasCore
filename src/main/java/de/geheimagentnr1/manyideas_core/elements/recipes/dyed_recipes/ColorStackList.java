package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;


public class ColorStackList implements ColorList {
	
	
	@NotNull
	private final ItemStack itemStack;
	
	ColorStackList( @NotNull ItemStack _stack ) {
		
		itemStack = _stack;
	}
	
	@NotNull
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return DyeBlockHelper.getColor( stack );
	}
	
	@NotNull
	@Override
	public ItemStack getStack( @NotNull Color color ) {
		
		return DyeBlockHelper.setColorToItemStack( itemStack.copy(), color );
	}
	
	//package-private
	@NotNull
	ItemStack getItemStack() {
		
		return itemStack;
	}
	
	@NotNull
	@Override
	public Collection<ItemStack> getItems() {
		
		return Collections.singletonList( itemStack );
	}
}
