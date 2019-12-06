package de.geheimagentnr1.manyideascore.elements.recipes.types;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;


public class SimpleRecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
	
	
	private final String name;
	
	public SimpleRecipeType( ResourceLocation registry_key ) {
		
		name = registry_key.toString();
	}
	
	@Override
	public String toString() {
		
		return name;
	}
}
