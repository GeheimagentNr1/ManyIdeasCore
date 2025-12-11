package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.ICustomIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;


public abstract class ColorIngredient<T extends ColorList> implements ICustomIngredient {
	
	
	@NotNull
	private final T ingrediant;
	
	ColorIngredient( @NotNull T _ingrediant ) {
		
		ingrediant = _ingrediant;
	}
	
	@Override
	public boolean isSimple() {
		
		return false;
	}
	
	@Override
	public boolean test( @NotNull ItemStack stack ) {
		
		return ingrediant.test( stack );
	}
	
	@Override
	public @NotNull Stream<ItemStack> getItems() {
		
		return ingrediant.getItems().stream();
	}
	
	//package-private
	@NotNull
	T getIngrediant() {
		
		return ingrediant;
	}
	
	public Color getColor( @NotNull ItemStack stack ) {
		
		return ingrediant.getColor( stack );
	}
	
	public ItemStack getStack( @NotNull Color color ) {
		
		return ingrediant.getStack( color );
	}
}
