package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


public class ColorStackIngredientSerializer {
	
	
	private static final Codec<ItemStack> ITEMSTACK_DYE_BLOCK_ITEM_CODEC = DyedRecipe.DYE_BLOCK_ITEM_CODEC
		.xmap( ItemStack::new, ItemStack::getItem );
	
	@NotNull
	public static final MapCodec<ColorStackIngredient> CODEC =
		RecordCodecBuilder.mapCodec( ( builder ) -> builder.group(
			ITEMSTACK_DYE_BLOCK_ITEM_CODEC.fieldOf( "color_item" )
				.forGetter( colorStackIngredient -> colorStackIngredient.getIngrediant().getItemStack() )
		).apply( builder, ColorStackIngredient::new ) );
}
