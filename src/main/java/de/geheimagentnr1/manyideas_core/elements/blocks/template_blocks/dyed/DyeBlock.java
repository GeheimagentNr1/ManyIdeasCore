package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class DyeBlock extends Block implements BlockItemInterface {
	
	
	protected DyeBlock( BlockBehaviour.Properties _properties, String registry_name ) {
		
		super( _properties );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockPlaceContext context ) {
		
		return DyeBlockHelper.getStateForPlacement( this, context );
	}
	
	@Override
	protected void createBlockStateDefinition( @Nonnull StateDefinition.Builder<Block, BlockState> builder ) {
		
		DyeBlockHelper.createBlockStateDefinition( builder );
	}
	
	@Override
	public ItemStack getCloneItemStack(
		BlockState state,
		HitResult target,
		BlockGetter level,
		BlockPos pos,
		Player player ) {
		
		return DyeBlockHelper.getItem( this, state );
	}
}
