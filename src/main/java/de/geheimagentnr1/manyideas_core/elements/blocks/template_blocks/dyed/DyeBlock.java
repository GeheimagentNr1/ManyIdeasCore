package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class DyeBlock extends Block {
	
	
	@SuppressWarnings( "SameParameterValue" )
	protected DyeBlock( Block.Properties properties, String registry_name ) {
		
		super( properties );
		setRegistryName( registry_name );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return DyeBlockHelper.getStateForPlacement( this, context );
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		DyeBlockHelper.fillStateContainer( builder );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public ItemStack getItem( IBlockReader worldIn, BlockPos pos, BlockState state ) {
		
		return DyeBlockHelper.getItem( this, state );
	}
}
