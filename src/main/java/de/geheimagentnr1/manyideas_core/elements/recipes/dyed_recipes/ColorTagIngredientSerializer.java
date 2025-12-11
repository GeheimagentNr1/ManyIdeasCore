package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.core.elements.recipes.EnumCodec;
import de.geheimagentnr1.manyideas_core.core.util.SimpleStringRepresentable;
import net.minecraft.core.registries.BuiltInRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;


public class ColorTagIngredientSerializer {
	
	
	@NotNull
	public static final MapCodec<ColorTagIngredient> CODEC =
		RecordCodecBuilder.mapCodec( ( builder ) -> builder.group(
			Codec.simpleMap(
					new EnumCodec<>( Color.class ),
					BuiltInRegistries.ITEM.byNameCodec(),
					Keyable.forStrings(
						() -> Arrays.stream( Color.values() )
							.map( SimpleStringRepresentable::getSerializedName )
					)
				).fieldOf( "color_tag" )
				.forGetter( colorTagIngredient -> colorTagIngredient.getIngrediant().getStackColors() )
		).apply( builder, ColorTagIngredient::new ) );
}
