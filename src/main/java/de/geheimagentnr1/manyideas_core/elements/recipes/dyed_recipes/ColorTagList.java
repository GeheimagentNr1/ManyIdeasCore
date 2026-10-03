package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class ColorTagList implements ColorList {
	
	
	@NotNull
	private final TreeMap<Item, Color> items;
	
	ColorTagList( @NotNull Map<Color, Item> colors ) {
		
		items = new TreeMap<>( Comparator.comparing( BuiltInRegistries.ITEM::getKey ) );
		colors.forEach( ( color, item ) -> {
			if( item != Items.AIR ) {
				items.put( item, color );
			}
		} );
	}
	
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return items.get( stack.getItem() );
	}
	
	@NotNull
	@Override
	public ItemStack getStack( @NotNull Color color ) {
		
		for( Map.Entry<Item, Color> entry : items.entrySet() ) {
			if( entry.getValue() == color ) {
				return new ItemStack( entry.getKey() );
			}
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean test( @NotNull ItemStack stack ) {
		
		return items.containsKey( stack.getItem() );
	}
	
	@NotNull
	public Map<Color, Item> getStackColors() {
		
		Map<Color, Item> colors = new EnumMap<>( Color.class );
		items.forEach( ( item, color ) -> colors.put( color, item ) );
		return colors;
	}
	
	@NotNull
	@Override
	public List<Item> getItems() {
		
		return new ArrayList<>( items.keySet() );
	}
}
