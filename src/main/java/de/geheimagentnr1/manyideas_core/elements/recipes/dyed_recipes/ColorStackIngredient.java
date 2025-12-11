package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.crafting.IngredientType;
import org.jetbrains.annotations.NotNull;


public class ColorStackIngredient extends ColorIngredient<ColorStackList> {
	
	
	@NotNull
	public static final String registry_name = "color_item";
	
	@NotNull
	public static final IngredientType<ColorStackIngredient> TYPE = new IngredientType<>(
		ColorStackIngredientSerializer.CODEC
	);
	
	//package-private
	ColorStackIngredient( @NotNull ItemStack _ingrediant ) {
		
		super( new ColorStackList( _ingrediant ) );
	}
	
	@Override
	public @NotNull IngredientType<?> getType() {
		
		return TYPE;
	}
}
