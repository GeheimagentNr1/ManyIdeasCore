package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModIngredientSerializersRegisterFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;


public class ColorIngredient extends Ingredient {
	
	
	@NotNull
	public static final String registry_name = "color";
	
	@NotNull
	private final ColorList ingrediant;
	
	//package-private
	ColorIngredient( @NotNull ColorList _ingrediant ) {
		
		super( Stream.of( _ingrediant ) );
		ingrediant = _ingrediant;
	}
	
	@Override
	public IIngredientSerializer<? extends Ingredient> serializer() {
		
		return ModIngredientSerializersRegisterFactory.COLOR;
	}
	
	//package-private
	@NotNull
	ColorList getIngrediant() {
		
		return ingrediant;
	}
	
	//package-private
	Color getColor( @NotNull ItemStack stack ) {
		
		return ingrediant.getColor( stack );
	}
	
	public ItemStack getStack( @NotNull Color color ) {
		
		return ingrediant.getStack( color );
	}
}
