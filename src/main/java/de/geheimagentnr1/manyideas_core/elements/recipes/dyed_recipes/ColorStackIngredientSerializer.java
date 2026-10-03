package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.NotNull;


public class ColorStackIngredientSerializer {
	
	
	@NotNull
	public static final MapCodec<ColorStackIngredient> CODEC =
		RecordCodecBuilder.mapCodec( ( builder ) -> builder.group(
			DyedRecipe.DYE_BLOCK_ITEM_CODEC.fieldOf( "color_item" )
				.forGetter( colorStackIngredient -> colorStackIngredient.getIngrediant().getItem() )
		).apply( builder, ColorStackIngredient::new ) );
}
