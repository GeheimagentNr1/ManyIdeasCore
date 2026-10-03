package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;


public class ColorStackList implements ColorList {
	
	
	@NotNull
	private final Item item;
	
	ColorStackList( @NotNull Item _item ) {
		
		item = _item;
	}
	
	@NotNull
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return DyeBlockHelper.getColor( stack );
	}
	
	@NotNull
	@Override
	public ItemStack getStack( @NotNull Color color ) {
		
		return DyeBlockHelper.setColor( new ItemStack( item ), color );
	}
	
	@Override
	public boolean test( @NotNull ItemStack stack ) {
		
		return stack.is( item );
	}
	
	//package-private
	@NotNull
	Item getItem() {
		
		return item;
	}
	
	@NotNull
	@Override
	public List<Item> getItems() {
		
		return Collections.singletonList( item );
	}
}
