package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;


public class ColorStackIngredientSerializer implements IIngredientSerializer<ColorStackIngredient> {
	
	
	private static final Codec<ItemStack> ITEMSTACK_DYE_BLOCK_ITEM_CODEC = DyedRecipe.DYE_BLOCK_ITEM_CODEC
		.xmap( ItemStack::new, ItemStack::getItem );
	
	private static final MapCodec<ColorStackIngredient> CODEC =
		RecordCodecBuilder.mapCodec( ( builder ) -> builder.group(
			ITEMSTACK_DYE_BLOCK_ITEM_CODEC.fieldOf( "color_item" )
				.forGetter( colorStackIngredient -> colorStackIngredient.getIngrediant().getItemStack() )
		).apply( builder, ColorStackIngredient::new ) );
	
	@NotNull
	@Override
	public MapCodec<? extends ColorStackIngredient> codec() {
		
		return CODEC;
	}
	
	@Override
	public void write( RegistryFriendlyByteBuf buffer, ColorStackIngredient value ) {
		
		ItemStack.STREAM_CODEC.encode( buffer, value.getIngrediant().getItemStack() );
	}
	
	@Override
	public ColorStackIngredient read( RegistryFriendlyByteBuf buffer ) {
		
		return new ColorStackIngredient( ItemStack.STREAM_CODEC.decode( buffer ) );
	}
}
