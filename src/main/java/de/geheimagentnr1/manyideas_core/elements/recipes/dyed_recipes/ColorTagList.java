package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class ColorTagList implements ColorList {
	
	
	@NotNull
	private final TreeMap<ItemStack, Color> stacks;
	
	//package-private
	ColorTagList( @NotNull TreeMap<ItemStack, Color> _stacks ) {
		
		stacks = _stacks;
	}
	
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return stacks.get( stack );
	}
	
	@NotNull
	@Override
	public ItemStack getStack( @NotNull Color color ) {
		
		for( Map.Entry<ItemStack, Color> entry : stacks.entrySet() ) {
			if( entry.getValue() == color ) {
				return entry.getKey().copy();
			}
		}
		return ItemStack.EMPTY;
	}
	
	//package-private
	@NotNull
	TreeMap<ItemStack, Color> getColorStacks() {
		
		return stacks;
	}
	
	@NotNull
	@Override
	public Collection<ItemStack> getItems() {
		
		return stacks.keySet();
	}
	
	@NotNull
	@Override
	public JsonObject serialize() {
		
		JsonObject jsonobject = new JsonObject();
		JsonArray items = new JsonArray();
		ArrayList<Color> colors = new ArrayList<>( Arrays.asList( Color.values() ) );
		stacks.forEach( ( stack, stackColor ) -> {
			JsonObject item = new JsonObject();
			item.addProperty(
				stackColor.getSerializedName(),
				BuiltInRegistries.ITEM.getKey( stack.getItem() ).toString()
			);
			items.add( item );
			colors.remove( stackColor );
		} );
		for( Color color : colors ) {
			JsonObject item = new JsonObject();
			item.addProperty( color.getSerializedName(), "" );
			items.add( item );
		}
		jsonobject.add( "color_tag", items );
		return jsonobject;
	}
}
