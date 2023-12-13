package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.ModIngredientSerializersRegisterFactory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;


public class ColorStackIngredient extends ColorIngredient<ColorStackList> {
	
	
	@NotNull
	public static final String registry_name = "color_item";
	
	//package-private
	ColorStackIngredient( @NotNull ItemStack _ingrediant ) {
		
		super( new ColorStackList( _ingrediant ) );
	}
	
	@Override
	public IIngredientSerializer<? extends Ingredient> serializer() {
		
		return ModIngredientSerializersRegisterFactory.COLOR_ITEM;
	}
}
