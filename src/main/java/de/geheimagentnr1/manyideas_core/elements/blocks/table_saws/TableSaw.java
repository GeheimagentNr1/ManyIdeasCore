package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TableSaw extends Block implements BlockItemInterface, BlockRenderTypeInterface {
	
	
	private static final DamageSource SAW = new DamageSource( "table_saw" );
	
	private static final VoxelShape SHAPE = VoxelShapes.or(
		Block.box( 0.0, 14.0, 0.0, 16.0, 15.75, 16.0 ),
		Block.box( 0.0, 0.0, 0.0, 2.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 0.0, 16.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 14.0, 16.0, 14.0, 16.0 ),
		Block.box( 0.0, 0.0, 14.0, 2.0, 14.0, 16.0 )
	);
	
	protected TableSaw( String registry_name ) {
		
		super( Properties.of( Material.WOOD ).strength( 2.5F ).sound( SoundType.WOOD ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.cutout();
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape(
		@Nonnull BlockState state,
		@Nonnull IBlockReader worldIn,
		@Nonnull BlockPos pos,
		@Nonnull ISelectionContext context ) {
		
		return SHAPE;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockItemUseContext context ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getHorizontalDirection()
		);
	}
	
	
	@Override
	public void stepOn( @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Entity entity ) {
		
		entity.hurt( SAW, 1.0F );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public ActionResultType use(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		@Nonnull PlayerEntity player,
		@Nonnull Hand hand,
		@Nonnull BlockRayTraceResult hit ) {
		
		player.openMenu( state.getMenuProvider( worldIn, pos ) );
		return ActionResultType.SUCCESS;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nullable
	@Override
	public INamedContainerProvider getMenuProvider(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos ) {
		
		return new SimpleNamedContainerProvider( ( windowID, playerInventory, player ) -> getContainer(
			windowID,
			playerInventory,
			IWorldPosCallable.create( worldIn, pos )
		), getContainerName() );
	}
	
	protected abstract Container getContainer(
		int windowID,
		PlayerInventory playerInventory,
		IWorldPosCallable worldPosCallable );
	
	protected abstract ITextComponent getContainerName();
	
	@Override
	protected void createBlockStateDefinition( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
}
