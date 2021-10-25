package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IIngredientSerializer;


@SuppressWarnings( "InterfaceWithOnlyOneDirectInheritor" )
public interface IngredientSerializer<I extends Ingredient> extends IIngredientSerializer<I> {
	
	
	//public
	default ResourceLocation getRegistryNameRL() {
		
		return new ResourceLocation( ManyIdeasCore.MODID, getRegistryName() );
	}
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	String getRegistryName();
}
