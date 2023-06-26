package de.geheimagentnr1.manyideas_core.elements.recipes.types;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;


public class SimpleRecipeType<T extends Recipe<?>> implements RecipeType<T> {
	
	
	@NotNull
	private final String name;
	
	public SimpleRecipeType( @NotNull String modId, @NotNull String registryName ) {
		
		name = new ResourceLocation( modId, registryName ).toString();
	}
	
	@NotNull
	@Override
	public String toString() {
		
		return name;
	}
}
