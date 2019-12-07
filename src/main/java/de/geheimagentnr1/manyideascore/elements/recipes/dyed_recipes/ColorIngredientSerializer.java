package de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes;

import com.google.gson.JsonObject;
import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.common.crafting.VanillaIngredientSerializer;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiConsumer;


public class ColorIngredientSerializer implements IIngredientSerializer<ColorIngredient> {
	
	
	public static final ColorIngredientSerializer INSTANCE  = new ColorIngredientSerializer();
	
	@Nonnull
	@Override
	public ColorIngredient parse( @Nonnull PacketBuffer buffer ) {
		
		if( buffer.readInt() == 0 ) {
			return new ColorIngredient( new ColorStackList( buffer.readItemStack() ) );
		} else {
			TreeMap<ItemStack, Color> colorStacks = new TreeMap<>(
				Comparator.comparing( o -> Objects.requireNonNull( o.getItem().getRegistryName() ) ) );
			int count = buffer.readInt();
			for( int i = 0; i < count; i++ ) {
				colorStacks.put( buffer.readItemStack(), Color.values()[buffer.readInt()] );
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
			buffer.writeItemStack( ( (ColorStackList)colorList).getItemStack() );
		} else {
			buffer.writeInt( 1 );
			TreeMap<ItemStack, Color> colorStacks = ( (ColorTagList)colorList ).getColorStacks();
			buffer.writeInt( colorStacks.size() );
			colorStacks.forEach( ( stack, color ) -> {
				buffer.writeItemStack( stack );
				buffer.writeInt( color.ordinal() );
			} );
		}
	}
}
