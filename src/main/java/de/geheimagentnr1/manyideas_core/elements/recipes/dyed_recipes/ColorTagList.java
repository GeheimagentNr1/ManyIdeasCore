package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.*;


public class ColorTagList implements ColorList {
	
	
	private final TreeMap<ItemStack, Color> stacks;
	
	//package-private
	ColorTagList( TreeMap<ItemStack, Color> _stacks ) {
		
		stacks = _stacks;
	}
	
	@Override
	public Color getColor( ItemStack stack ) {
		
		return stacks.get( stack );
	}
	
	@Override
	public ItemStack getStack( Color color ) {
		
		for( Map.Entry<ItemStack, Color> entry : stacks.entrySet() ) {
			if( entry.getValue() == color ) {
				return entry.getKey().copy();
			}
		}
		return ItemStack.EMPTY;
	}
	
	//package-private
	TreeMap<ItemStack, Color> getColorStacks() {
		
		return stacks;
	}
	
	@Nonnull
	@Override
	public Collection<ItemStack> getStacks() {
		
		return stacks.keySet();
	}
	
	@Nonnull
	@Override
	public JsonObject serialize() {
		
		JsonObject jsonobject = new JsonObject();
		JsonArray items = new JsonArray();
		ArrayList<Color> colors = new ArrayList<>( Arrays.asList( Color.values() ) );
		stacks.forEach( ( stack, stackColor ) -> {
			JsonObject item = new JsonObject();
			item.addProperty(
				stackColor.getName(),
				Objects.requireNonNull( stack.getItem().getRegistryName() ).toString()
			);
			items.add( item );
			colors.remove( stackColor );
		} );
		for( Color color : colors ) {
			JsonObject item = new JsonObject();
			item.addProperty( color.getName(), "" );
			items.add( item );
		}
		jsonobject.add( "color_tag", items );
		return jsonobject;
	}
}
