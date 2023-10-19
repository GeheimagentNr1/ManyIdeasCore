package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlockItem;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;


public class ColorStackList implements ColorList {
	
	
	private static final Codec<ItemStack> ITEMSTACK_DYE_BLOCK_ITEM_CODEC = ExtraCodecs.validate(
		BuiltInRegistries.ITEM.byNameCodec(),
		( item ) -> item instanceof DyeBlockItem
			? DataResult.success( item )
			: DataResult.error( () -> "Non DyeBlockItem ingredient not allowed here" )
	).xmap( ItemStack::new, ItemStack::getItem );
	
	public static final Codec<ColorStackList> CODEC = RecordCodecBuilder.create( ( builder ) -> builder.group(
		ITEMSTACK_DYE_BLOCK_ITEM_CODEC.fieldOf( "color_item" ).forGetter( ColorStackList::getItemStack )
	).apply( builder, ColorStackList::new ) );
	
	@NotNull
	private final ItemStack itemStack;
	
	//package-private
	@NotNull ColorStackList( ItemStack _stack ) {
		
		itemStack = _stack;
	}
	
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return DyeBlockHelper.getColor( stack );
	}
	
	@Override
	public ItemStack getStack( @NotNull Color color ) {
		
		return DyeBlockHelper.setColorToItemStack( itemStack.copy(), color );
	}
	
	//package-private
	@NotNull
	ItemStack getItemStack() {
		
		return itemStack;
	}
	
	@NotNull
	@Override
	public Collection<ItemStack> getItems() {
		
		return Collections.singletonList( itemStack );
	}
}
