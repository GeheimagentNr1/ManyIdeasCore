package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.minecraft_forge_api.elements.recipes.EnumCodec;
import de.geheimagentnr1.minecraft_forge_api.util.SimpleStringRepresentable;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;


public class ColorTagIngredientSerializer implements IIngredientSerializer<ColorTagIngredient> {
	
	
	private static final Codec<ColorTagIngredient> CODEC = RecordCodecBuilder.create( ( builder ) -> builder.group(
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
	
	@NotNull
	@Override
	public Codec<? extends ColorTagIngredient> codec() {
		
		return CODEC;
	}
	
	@NotNull
	@Override
	public ColorTagIngredient read( @NotNull FriendlyByteBuf buffer ) {
		
		TreeMap<ItemStack, Color> colorStacks = new TreeMap<>(
			Comparator.comparing( o -> BuiltInRegistries.ITEM.getKey( o.getItem() ) )
		);
		int count = buffer.readInt();
		for( int i = 0; i < count; i++ ) {
			colorStacks.put( buffer.readItem(), Color.values()[buffer.readInt()] );
		}
		return new ColorTagIngredient( colorStacks );
	}
	
	@Override
	public void write( @NotNull FriendlyByteBuf buffer, @NotNull ColorTagIngredient ingredient ) {
		
		TreeMap<ItemStack, Color> colorStacks = ingredient.getIngrediant().getColorStacks();
		buffer.writeInt( colorStacks.size() );
		colorStacks.forEach( ( stack, color ) -> {
			buffer.writeItem( stack );
			buffer.writeInt( color.ordinal() );
		} );
	}
}
