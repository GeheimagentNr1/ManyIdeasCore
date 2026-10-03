package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;


//package-private
interface ColorList {
	
	
	//public
	Color getColor( @NotNull ItemStack stack );
	
	//public
	ItemStack getStack( @NotNull Color color );
	
	//public
	boolean test( @NotNull ItemStack stack );
	
	//public
	//Items instead of ItemStacks: since 26.1 ingredients are parsed before item components are bound, so no ItemStack
	//may be created while parsing.
	List<Item> getItems();
}
