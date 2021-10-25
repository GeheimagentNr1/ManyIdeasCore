package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.gson.JsonElement;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import javax.annotation.Nonnull;
import java.util.stream.Stream;


public class ColorIngredient extends Ingredient {
	
	
	//package-private
	static final String registry_name = "color";
	
	private final ColorList ingrediant;
	
	//package-private
	ColorIngredient( ColorList _ingrediant ) {
		
		super( Stream.of( _ingrediant ) );
		ingrediant = _ingrediant;
	}
	
	@Nonnull
	@Override
	public JsonElement serialize() {
		
		return ingrediant.serialize();
	}
	
	@Nonnull
	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		
		return ColorIngredientSerializer.INSTANCE;
	}
	
	//package-private
	ColorList getIngrediant() {
		
		return ingrediant;
	}
	
	//package-private
	Color getColor( ItemStack stack ) {
		
		return ingrediant.getColor( stack );
	}
	
	public ItemStack getStack( Color color ) {
		
		return ingrediant.getStack( color );
	}
}
