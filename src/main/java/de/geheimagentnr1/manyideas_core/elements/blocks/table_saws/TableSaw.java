package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


@SuppressWarnings( "AbstractClassExtendsConcreteClass" )
public abstract class TableSaw extends Block implements BlockItemInterface, BlockRenderTypeInterface {
	
	
	private static final DamageSource SAW = new DamageSource( "table_saw" );
	
	private static final VoxelShape SHAPE = Shapes.or(
		Block.box( 0.0, 14.0, 0.0, 16.0, 15.75, 16.0 ),
		Block.box( 0.0, 0.0, 0.0, 2.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 0.0, 16.0, 14.0, 2.0 ),
		Block.box( 14.0, 0.0, 14.0, 16.0, 14.0, 16.0 ),
		Block.box( 0.0, 0.0, 14.0, 2.0, 14.0, 16.0 )
	);
	
	protected TableSaw( String registry_name ) {
		
		super( BlockBehaviour.Properties.of( Material.WOOD )
			.strength( 2.5F )
			.harvestTool( ToolType.AXE )
			.sound( SoundType.WOOD ) );
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
		@Nonnull BlockGetter level,
		@Nonnull BlockPos pos,
		@Nonnull CollisionContext context ) {
		
		return SHAPE;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @Nonnull BlockPlaceContext context ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getHorizontalDirection()
		);
	}
	
	@Override
	public void stepOn(
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull BlockState state,
		@Nonnull Entity entity ) {
		
		entity.hurt( SAW, 1.0F );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public InteractionResult use(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos,
		@Nonnull Player player,
		@Nonnull InteractionHand hand,
		@Nonnull BlockHitResult hitResult ) {
		
		player.openMenu( state.getMenuProvider( level, pos ) );
		return InteractionResult.SUCCESS;
	}
	
	@SuppressWarnings( "deprecation" )
	@Nullable
	@Override
	public MenuProvider getMenuProvider(
		@Nonnull BlockState state,
		@Nonnull Level level,
		@Nonnull BlockPos pos ) {
		
		return new SimpleMenuProvider( ( menuId, inventory, player ) -> getMenu(
			menuId,
			inventory,
			ContainerLevelAccess.create( level, pos )
		), getContainerName() );
	}
	
	protected abstract AbstractContainerMenu getMenu(
		int menuId,
		Inventory inventory,
		ContainerLevelAccess containerLevelAccess );
	
	protected abstract Component getContainerName();
	
	@Override
	protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
}
