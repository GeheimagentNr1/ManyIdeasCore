package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;


//package-private
interface ColorList extends Ingredient.Value {
	
	
	//public
	Color getColor( ItemStack stack );
	
	//public
	ItemStack getStack( Color color );
}
