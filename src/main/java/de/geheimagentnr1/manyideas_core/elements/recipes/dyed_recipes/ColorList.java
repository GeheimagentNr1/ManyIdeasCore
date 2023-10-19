package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


//package-private
interface ColorList extends Ingredient.Value {
	
	
	Codec<ColorList> CODEC = ExtraCodecs.xor(
		ColorStackList.CODEC,
		ColorTagList.CODEC
	).xmap(
		either -> either.map(
			Function.identity(),
			Function.identity()
		),
		colorList -> {
			if( colorList instanceof ColorStackList colorStackList ) {
				return Either.left( colorStackList );
			} else {
				if( colorList instanceof ColorTagList colorTagList ) {
					return Either.right( colorTagList );
				} else {
					throw new UnsupportedOperationException(
						"This is neither an color stack value nor a color tag value." );
				}
			}
		}
	);
	
	//public
	Color getColor( @NotNull ItemStack stack );
	
	//public
	ItemStack getStack( @NotNull Color color );
}
