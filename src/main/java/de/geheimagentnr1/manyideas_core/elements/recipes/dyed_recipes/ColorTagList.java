package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Keyable;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.minecraft_forge_api.elements.recipes.EnumCodec;
import de.geheimagentnr1.minecraft_forge_api.util.SimpleStringRepresentable;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class ColorTagList implements ColorList {
	
	
	static final Codec<ColorTagList> CODEC = RecordCodecBuilder.create( ( builder ) -> builder.group(
		Codec.simpleMap(
				new EnumCodec<>( Color.class ),
				BuiltInRegistries.ITEM.byNameCodec(),
				Keyable.forStrings(
					() -> Arrays.stream( Color.values() )
						.map( SimpleStringRepresentable::getSerializedName )
				)
			).fieldOf( "color_tag" )
			.forGetter( ColorTagList::getStackColors )
	).apply( builder, ColorTagList::new ) );
	
	@NotNull
	private final TreeMap<ItemStack, Color> stacks;
	
	//package-private
	ColorTagList( @NotNull TreeMap<ItemStack, Color> _stacks ) {
		
		stacks = _stacks;
	}
	
	private ColorTagList( @NotNull Map<Color, Item> colors ) {
		
		stacks = new TreeMap<>( Comparator.comparing( o -> BuiltInRegistries.ITEM.getKey( o.getItem() ) ) );
		colors.forEach( ( color, item ) -> {
			if( item != Items.AIR ) {
				stacks.put( new ItemStack( item ), color );
			}
		} );
	}
	
	@Override
	public Color getColor( @NotNull ItemStack stack ) {
		
		return stacks.get( stack );
	}
	
	@NotNull
	@Override
	public ItemStack getStack( @NotNull Color color ) {
		
		for( Map.Entry<ItemStack, Color> entry : stacks.entrySet() ) {
			if( entry.getValue() == color ) {
				return entry.getKey().copy();
			}
		}
		return ItemStack.EMPTY;
	}
	
	//package-private
	@NotNull
	TreeMap<ItemStack, Color> getColorStacks() {
		
		return stacks;
	}
	
	@NotNull
	private Map<Color, Item> getStackColors() {
		
		Map<Color, Item> colors = new EnumMap<>( Color.class );
		stacks.forEach( ( stack, color ) -> colors.put( color, stack.getItem() ) );
		return colors;
	}
	
	@NotNull
	@Override
	public Collection<ItemStack> getItems() {
		
		return stacks.keySet();
	}
}
