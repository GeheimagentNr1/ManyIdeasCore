package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideascore.util.DyeBlockHelper;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;


public class ColorStackList implements ColorList {
	
	
	private final ItemStack itemStack;
	
	public ColorStackList( ItemStack _stack ) {
		
		itemStack = _stack;
	}
	
	@Override
	public Color getColor( ItemStack stack ) {
		
		return DyeBlockHelper.getColor( stack );
	}
	
	@Nonnull
	@Override
	public Collection<ItemStack> getStacks() {
		
		return Collections.singletonList( itemStack );
	}
	
	@Nonnull
	@Override
	public JsonObject serialize() {
		
		JsonObject jsonobject = new JsonObject();
		jsonobject.addProperty("item", Objects.requireNonNull( itemStack.getItem().getRegistryName() ).toString() );
		return jsonobject;
	}
}
