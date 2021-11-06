package de.geheimagentnr1.manyideas_core.util;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed.DyeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;


public class DyeBlockHelper {
	
	
	private static final String dyeNBTname = "dye";
	
	private static final String colorNBTname = "color";
	
	private static String getColorNameFromStack( ItemStack stack ) {
		
		return stack.getOrCreateTagElement( dyeNBTname ).getString( colorNBTname );
	}
	
	public static String getColorName( ItemStack stack ) {
		
		String color = getColorNameFromStack( stack );
		return color.isEmpty() ? Color.WHITE.getSerializedName() : color;
	}
	
	public static Color getColor( ItemStack stack ) {
		
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
	
	public static ItemStack setColorToItemStack( ItemStack stack, Color color ) {
		
		stack.getOrCreateTagElement( dyeNBTname ).putString( colorNBTname, color.getSerializedName() );
		return stack;
	}
	
	public static BlockState getStateForPlacement( DyeBlock block, BlockItemUseContext context ) {
		
		return block.defaultBlockState().setValue(
			ModBlockStateProperties.COLOR,
			getColor( context.getItemInHand() )
		);
	}
	
	public static void createBlockStateDefinition( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( ModBlockStateProperties.COLOR );
	}
	
	public static ItemStack getItem( DyeBlock block, BlockState state ) {
		
		return setColorToItemStack( new ItemStack( block ), state.getValue( ModBlockStateProperties.COLOR ) );
	}
	
	public static ItemStack createItemStackOfItem( Item item, Color color ) {
		
		return setColorToItemStack( new ItemStack( item ), color );
	}
}
