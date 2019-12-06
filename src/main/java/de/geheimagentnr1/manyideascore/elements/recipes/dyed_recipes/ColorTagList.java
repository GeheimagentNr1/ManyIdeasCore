package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;


public class ColorTagList implements ColorList {
	
	
	private final TreeMap<ItemStack, Color> stacks;/* = new TreeMap<>(
		Comparator.comparing( o -> Objects.requireNonNull( o.getItem().getRegistryName() ) ) );*/
	
	public ColorTagList( TreeMap<ItemStack, Color> _stacks ) {
		
		stacks = _stacks;
	}
	
	@Override
	public Color getColor( ItemStack stack ) {
		
		return stacks.get( stack );
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
		stacks.forEach( ( stack, color ) -> {
			JsonObject item = new JsonObject();
			item.addProperty( "item", Objects.requireNonNull( stack.getItem().getRegistryName() ).toString() );
			item.addProperty( "color", color.getName() );
			items.add( item );
		} );
		jsonobject.add( "color_tag", items );
		return jsonobject;
	}
}
