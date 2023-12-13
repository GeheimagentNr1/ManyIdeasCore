package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;


public class ColorStackIngredientSerializer implements IIngredientSerializer<ColorStackIngredient> {
	
	
	private static final Codec<ItemStack> ITEMSTACK_DYE_BLOCK_ITEM_CODEC = DyedRecipe.DYE_BLOCK_ITEM_CODEC
		.xmap( ItemStack::new, ItemStack::getItem );
	
	private static final Codec<ColorStackIngredient> CODEC = RecordCodecBuilder.create( ( builder ) -> builder.group(
		ITEMSTACK_DYE_BLOCK_ITEM_CODEC.fieldOf( "color_item" )
			.forGetter( colorStackIngredient -> colorStackIngredient.getIngrediant().getItemStack() )
	).apply( builder, ColorStackIngredient::new ) );
	
	@NotNull
	@Override
	public Codec<? extends ColorStackIngredient> codec() {
		
		return CODEC;
	}
	
	@NotNull
	@Override
	public ColorStackIngredient read( @NotNull FriendlyByteBuf buffer ) {
		
		return new ColorStackIngredient( buffer.readItem() );
	}
	
	@Override
	public void write( @NotNull FriendlyByteBuf buffer, @NotNull ColorStackIngredient ingredient ) {
		
		buffer.writeItem( ingredient.getIngrediant().getItemStack() );
	}
}
