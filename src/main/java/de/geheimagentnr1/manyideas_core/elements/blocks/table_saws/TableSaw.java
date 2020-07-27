package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TableSaw extends Block {
	
	
	private static final DamageSource SAW = new DamageSource( "table_saw" );
	
	private final TranslationTextComponent CONTAINER_NAME;
	
	private static final VoxelShape SHAPE = VoxelShapes.or( Block.makeCuboidShape( 0.0, 14.0, 0.0, 16.0, 15.75, 16.0 ),
		Block.makeCuboidShape( 0.0, 0.0, 0.0, 2.0, 14.0, 2.0 ),
		Block.makeCuboidShape( 14.0, 0.0, 0.0, 16.0, 14.0, 2.0 ),
		Block.makeCuboidShape( 14.0, 0.0, 14.0, 16.0, 14.0, 16.0 ),
		Block.makeCuboidShape( 0.0, 0.0, 14.0, 2.0, 14.0, 16.0 ) );
	
	protected TableSaw( String registry_name ) {
		
		super( Block.Properties.create( Material.WOOD ).hardnessAndResistance( 2.5F ).sound( SoundType.WOOD ) );
		setRegistryName( registry_name );
		CONTAINER_NAME = TranslationKeyHelper.generateContainerTranslationText( ManyIdeasCore.MODID, registry_name );
	}
	
	@Nonnull
	@Override
	public BlockRenderLayer getRenderLayer() {
		
		return BlockRenderLayer.CUTOUT;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape( @Nonnull BlockState state, @Nonnull IBlockReader worldIn, @Nonnull BlockPos pos,
		@Nonnull ISelectionContext context ) {
		
		return SHAPE;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return getDefaultState().with( BlockStateProperties.HORIZONTAL_FACING,
			context.getPlacementHorizontalFacing() );
	}
	
	@Override
	public void onEntityWalk( @Nonnull World worldIn, @Nonnull BlockPos pos, Entity entityIn ) {
		
		entityIn.attackEntityFrom( SAW, 1.0F );
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public boolean onBlockActivated( BlockState state, @Nonnull World worldIn, @Nonnull BlockPos pos,
		PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit ) {
		
		player.openContainer( state.getContainer( worldIn, pos ) );
		return true;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nullable
	@Override
	public INamedContainerProvider getContainer( @Nonnull BlockState state, @Nonnull World worldIn,
		@Nonnull BlockPos pos ) {
		
		return new SimpleNamedContainerProvider( ( windowID, playerInventory, player ) ->
			getContainer( windowID, playerInventory, IWorldPosCallable.of( worldIn, pos ) ),
			CONTAINER_NAME );
	}
	
	//package-private
	protected abstract Container getContainer( int windowID, PlayerInventory playerInventory,
		IWorldPosCallable worldPosCallable );
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
}
