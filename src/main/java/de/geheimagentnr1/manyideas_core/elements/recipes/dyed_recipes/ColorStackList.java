package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.core.Registry;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


public class ColorStackList implements ColorList {
	
	
	private final ItemStack itemStack;
	
	//package-private
	ColorStackList( ItemStack _stack ) {
		
		itemStack = _stack;
	}
	
	@Override
	public Color getColor( ItemStack stack ) {
		
		return DyeBlockHelper.getColor( stack );
	}
	
	@Override
	public ItemStack getStack( Color color ) {
		
		return DyeBlockHelper.setColorToItemStack( itemStack.copy(), color );
	}
	
	//package-private
	ItemStack getItemStack() {
		
		return itemStack;
	}
	
	@Nonnull
	@Override
	public Collection<ItemStack> getItems() {
		
		return Collections.singletonList( itemStack );
	}
	
	@Nonnull
	@Override
	public JsonObject serialize() {
		
		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty(
			"color_item",
			Objects.requireNonNull( Registry.ITEM.getKey( itemStack.getItem() ) ).toString()
		);
		return jsonobject;
	}
}
