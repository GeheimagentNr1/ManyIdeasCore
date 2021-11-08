package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredientSerializer;
import net.minecraft.world.item.crafting.Ingredient;


@SuppressWarnings( { "PublicStaticArrayField", "unchecked" } )
public class IngredientSerializers {
	
	//Color
	
	public static final ColorIngredientSerializer COLOR = new ColorIngredientSerializer();
	
	public static final IngredientSerializer<? extends Ingredient>[] INGREDIENTS = new IngredientSerializer[] {
		//NBT
		COLOR,
	};
}
