package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.common.crafting.VanillaIngredientSerializer;

import java.util.stream.Stream;


public class ColorIngredient extends Ingredient {
	
	
	private final ColorList ingrediant;
	
	public ColorIngredient( ColorList _ingrediant ) {
		
		super( Stream.of( _ingrediant ) );
		ingrediant = _ingrediant;
	}
	
	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		
		return new VanillaIngredientSerializer();
	}
	
	public Color getColor( ItemStack stack ) {
		
		return ingrediant.getColor( stack );
	}
}
