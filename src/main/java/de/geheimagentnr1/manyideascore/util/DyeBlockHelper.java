package de.geheimagentnr1.manyideascore.util;

import de.geheimagentnr1.manyideascore.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideascore.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideascore.elements.blocks.template_blocks.dyed.DyeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;


public class DyeBlockHelper {
	
	
	private final static String dyeNBTname = "dye";
	
	private final static String colorNBTname = "color";
	
	private static String getColorNameFromStack( ItemStack stack ) {
		
		return stack.getOrCreateChildTag( dyeNBTname ).getString( colorNBTname );
	}
	
	public static String getColorName( ItemStack stack ) {
		
		String color = getColorNameFromStack( stack );
		return color.isEmpty() ? Color.WHITE.getName() : color;
	}
	
	public static Color getColor( ItemStack stack ) {
		
		String colorName = getColorName( stack );
		if( colorName.isEmpty() ) {
			return Color.WHITE;
		}
		Color resultColor = Color.WHITE;
		
		for( Color color : Color.values() ) {
			if( color.getName().equals( colorName ) ) {
				resultColor = color;
				break;
			}
		}
		return resultColor;
	}
	
	public static ItemStack setColorToItemStack( ItemStack stack, Color dyeColor ) {
		
		stack.getOrCreateChildTag( dyeNBTname ).putString( colorNBTname, dyeColor.getName() );
		return stack;
	}
	
	public static BlockState getStateForPlacement( DyeBlock block, BlockItemUseContext context ) {
		
		return block.getDefaultState().with( ModBlockStateProperties.COLOR, getColor( context.getItem() ) );
	}
	
	public static void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( ModBlockStateProperties.COLOR );
	}
	
	public static ItemStack getItem( DyeBlock block, BlockState state ) {
		
		return setColorToItemStack( new ItemStack( block ), state.get( ModBlockStateProperties.COLOR ) );
	}
	
	public static ItemStack createItemStackOfItem( Item item, Color color ) {
		
		return setColorToItemStack( new ItemStack( item ), color );
	}
}
