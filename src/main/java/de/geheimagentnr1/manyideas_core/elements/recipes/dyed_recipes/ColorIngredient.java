package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.ingredients.AbstractIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;


abstract class ColorIngredient<T extends ColorList> extends AbstractIngredient {
	
	
	@NotNull
	private final T ingrediant;
	
	ColorIngredient( @NotNull T _ingrediant ) {
		
		super( Stream.of( _ingrediant ) );
		ingrediant = _ingrediant;
	}
	
	@Override
	public boolean isSimple() {
		
		return false;
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
