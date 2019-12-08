package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;


//package-private
interface ColorList extends Ingredient.IItemList {
	
	
	//public
	Color getColor( ItemStack stack );
}
