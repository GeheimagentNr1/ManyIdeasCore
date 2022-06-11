package de.geheimagentnr1.manyideas_core.elements.recipes.types;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;


public class SimpleRecipeType<T extends Recipe<?>> implements RecipeType<T> {
	
	
	private final String name;
	
	public SimpleRecipeType( String modId, String registryName ) {
		
		name = new ResourceLocation( modId, registryName ).toString();
	}
	
	@Override
	public String toString() {
		
		return name;
	}
}
