package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings( "InterfaceWithOnlyOneDirectInheritor" )
public interface IngredientSerializer<I extends Ingredient> extends IIngredientSerializer<I> {
	
	
	//public
	@NotNull
	default ResourceLocation getRegistryNameRL() {
		
		return new ResourceLocation( ManyIdeasCore.MODID, getRegistryName() );
	}
	
	//public
	@SuppressWarnings( "SameReturnValue" )
	String getRegistryName();
}
