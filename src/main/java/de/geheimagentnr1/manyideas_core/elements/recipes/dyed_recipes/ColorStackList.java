package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


public class ColorStackList implements ColorList {
	
	
	@NotNull
	private final ItemStack itemStack;
	
	//package-private
	@NotNull ColorStackList( ItemStack _stack ) {
		
		itemStack = _stack;
	}
	
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return DyeBlockHelper.getColor( stack );
	}
	
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
	
	@NotNull
	@Override
	public JsonObject serialize() {
		
		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty(
			"color_item",
			Objects.requireNonNull( BuiltInRegistries.ITEM.getKey( itemStack.getItem() ) ).toString()
		);
		return jsonobject;
	}
}
