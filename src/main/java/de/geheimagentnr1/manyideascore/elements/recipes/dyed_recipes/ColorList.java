package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;


public interface ColorList extends Ingredient.IItemList {
	
	//public
	Color getColor( ItemStack stack );
}
