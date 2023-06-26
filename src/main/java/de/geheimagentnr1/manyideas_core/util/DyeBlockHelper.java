package de.geheimagentnr1.manyideas_core.util;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;


public class DyeBlockHelper {
	
	
	@NotNull
	private static final String dyeNBTname = "dye";
	
	@NotNull
	private static final String colorNBTname = "color";
	
	private static String getColorNameFromStack( @NotNull ItemStack stack ) {
		
		return stack.getOrCreateTagElement( dyeNBTname ).getString( colorNBTname );
	}
	
	@NotNull
	public static String getColorName( @NotNull ItemStack stack ) {
		
		String color = getColorNameFromStack( stack );
		return color.isEmpty() ? Color.WHITE.getSerializedName() : color;
	}
	
	@NotNull
	public static Color getColor( @NotNull ItemStack stack ) {
		
		String colorName = getColorName( stack );
		if( colorName.isEmpty() ) {
			return Color.WHITE;
		}
		Color resultColor = Color.WHITE;
		
		for( Color color : Color.values() ) {
			if( color.getSerializedName().equals( colorName ) ) {
				resultColor = color;
				break;
			}
		}
		return resultColor;
	}
	
	@NotNull
	public static ItemStack setColorToItemStack( @NotNull ItemStack stack, @NotNull Color color ) {
		
		stack.getOrCreateTagElement( dyeNBTname ).putString( colorNBTname, color.getSerializedName() );
		return stack;
	}
	
	@NotNull
	public static BlockState getStateForPlacement( @NotNull DyeBlock block, @NotNull BlockPlaceContext context ) {
		
		return block.defaultBlockState().setValue(
			ModBlockStateProperties.COLOR,
			getColor( context.getItemInHand() )
		);
	}
	
	public static void createBlockStateDefinition( @NotNull StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( ModBlockStateProperties.COLOR );
	}
	
	@NotNull
	public static ItemStack getItem( @NotNull DyeBlock block, @NotNull BlockState state ) {
		
		return setColorToItemStack( new ItemStack( block ), state.getValue( ModBlockStateProperties.COLOR ) );
	}
	
	@NotNull
	public static ItemStack createItemStackOfItem( @NotNull Item item, @NotNull Color color ) {
		
		return setColorToItemStack( new ItemStack( item ), color );
	}
}
