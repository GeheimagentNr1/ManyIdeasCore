package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.TreeMap;


public class ColorIngredientSerializer implements IIngredientSerializer<ColorIngredient> {
	
	
	public static final Codec<ColorIngredient> CODEC = ColorList.CODEC.flatComapMap(
		ColorIngredient::new,
		colorIngredient -> DataResult.success( colorIngredient.getIngrediant() )
	);
	
	@NotNull
	@Override
	public Codec<? extends ColorIngredient> codec() {
		
		return CODEC;
	}
	
	@NotNull
	@Override
	public ColorIngredient read( @NotNull FriendlyByteBuf buffer ) {
		
		if( buffer.readInt() == 0 ) {
			return new ColorIngredient( new ColorStackList( buffer.readItem() ) );
		} else {
			TreeMap<ItemStack, Color> colorStacks =
				new TreeMap<>( Comparator.comparing( o -> BuiltInRegistries.ITEM.getKey( o.getItem() ) ) );
			int count = buffer.readInt();
			for( int i = 0; i < count; i++ ) {
				colorStacks.put( buffer.readItem(), Color.values()[buffer.readInt()] );
			}
			return new ColorIngredient( new ColorTagList( colorStacks ) );
		}
	}
	
	@Override
	public void write( @NotNull FriendlyByteBuf buffer, @NotNull ColorIngredient ingredient ) {
		
		ColorList colorList = ingredient.getIngrediant();
		if( colorList instanceof ColorStackList ) {
			buffer.writeInt( 0 );
			buffer.writeItem( ( (ColorStackList)colorList ).getItemStack() );
		} else {
			buffer.writeInt( 1 );
			TreeMap<ItemStack, Color> colorStacks = ( (ColorTagList)colorList ).getColorStacks();
			buffer.writeInt( colorStacks.size() );
			colorStacks.forEach( ( stack, color ) -> {
				buffer.writeItem( stack );
				buffer.writeInt( color.ordinal() );
			} );
		}
	}
}
