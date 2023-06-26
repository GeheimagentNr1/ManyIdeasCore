package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;


//package-private
interface ColorList extends Ingredient.Value {
	
	
	//public
	Color getColor( @NotNull ItemStack stack );
	
	//public
	ItemStack getStack( @NotNull Color color );
}
