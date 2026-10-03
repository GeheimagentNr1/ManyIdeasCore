package de.geheimagentnr1.manyideas_core.core.elements.recipes.types;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;


public class SimpleRecipeType<T extends Recipe<?>> implements RecipeType<T> {
	
	
	@NotNull
	private final Identifier resourceLocation;
	
	public SimpleRecipeType( @NotNull String modId, @NotNull String name ) {
		
		this.resourceLocation = Identifier.fromNamespaceAndPath( modId, name );
	}
	
	@NotNull
	@Override
	public String toString() {
		
		return resourceLocation.toString();
	}
}
