package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class DyeBlock extends Block implements BlockItemInterface {
	
	
	protected DyeBlock( @NotNull BlockBehaviour.Properties _properties ) {
		
		super( _properties );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @NotNull BlockPlaceContext context ) {
		
		return DyeBlockHelper.getStateForPlacement( this, context );
	}
	
	@Override
	protected void createBlockStateDefinition( @NotNull StateDefinition.Builder<Block, BlockState> builder ) {
		
		DyeBlockHelper.createBlockStateDefinition( builder );
	}
	
	@NotNull
	@Override
	public ItemStack getCloneItemStack(
		@NotNull BlockState state,
		@NotNull HitResult target,
		@NotNull BlockGetter level,
		@NotNull BlockPos pos,
		@NotNull Player player ) {
		
		return DyeBlockHelper.getItem( this, state );
	}
	
	@NotNull
	@Override
	public Item getBlockItem( @NotNull Block block, @NotNull Item.Properties properties ) {
		
		return new DyeBlockItem( block, properties );
	}
}
