package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModIngredientSerializersRegisterFactory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;


public class ColorTagIngredient extends ColorIngredient<ColorTagList> {
	
	
	@NotNull
	public static final String registry_name = "color_tag";
	
	ColorTagIngredient( @NotNull TreeMap<ItemStack, Color> _ingrediant ) {
		
		super( new ColorTagList( _ingrediant ) );
	}
	
	ColorTagIngredient( @NotNull Map<Color, Item> colors ) {
		
		super( new ColorTagList( colors ) );
	}
	
	@Override
	public IIngredientSerializer<? extends Ingredient> serializer() {
		
		return ModIngredientSerializersRegisterFactory.COLOR_TAG;
	}
}
