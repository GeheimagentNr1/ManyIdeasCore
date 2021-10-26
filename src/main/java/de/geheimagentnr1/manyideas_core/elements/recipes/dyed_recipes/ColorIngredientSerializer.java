package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.recipes.IngredientSerializer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;


public class ColorIngredientSerializer implements IngredientSerializer<ColorIngredient> {
	
	
	@Override
	public String getRegistryName() {
		
		return ColorIngredient.registry_name;
	}
	
	@Nonnull
	@Override
	public ColorIngredient parse( @Nonnull PacketBuffer buffer ) {
		
		if( buffer.readInt() == 0 ) {
			return new ColorIngredient( new ColorStackList( buffer.readItem() ) );
		} else {
			TreeMap<ItemStack, Color> colorStacks =
				new TreeMap<>( Comparator.comparing( o -> Objects.requireNonNull( o.getItem().getRegistryName() ) ) );
			int count = buffer.readInt();
			for( int i = 0; i < count; i++ ) {
				colorStacks.put( buffer.readItem(), Color.values()[buffer.readInt()] );
			}
			return new ColorIngredient( new ColorTagList( colorStacks ) );
		}
	}
	
	@Nonnull
	@Override
	public ColorIngredient parse( @Nonnull JsonObject json ) {
		
		return (ColorIngredient)new DyedRecipeSerializer().deserializeIngredient( json );
	}
	
	@Override
	public void write( @Nonnull PacketBuffer buffer, ColorIngredient ingredient ) {
		
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
