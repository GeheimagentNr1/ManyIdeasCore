package de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class DyeCraftingTable extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "dye_crafting_table";
	
	private static final VoxelShape SHAPE = VoxelShapes.or(
		Block.makeCuboidShape( 0.0, 14.0, 0.0, 16.0, 15.75, 16.0 ),
		Block.makeCuboidShape( 0.0, 0.0, 0.0, 2.0, 14.0, 2.0 ),
		Block.makeCuboidShape( 14.0, 0.0, 0.0, 16.0, 14.0, 2.0 ),
		Block.makeCuboidShape( 14.0, 0.0, 14.0, 16.0, 14.0, 16.0 ),
		Block.makeCuboidShape( 0.0, 0.0, 14.0, 2.0, 14.0, 16.0 )
	);
	
	public DyeCraftingTable() {
		
		super( Block.Properties.create( Material.WOOD ).hardnessAndResistance( 2.5F ).sound( SoundType.WOOD ) );
		setRegistryName( registry_name );
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
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return getDefaultState().with( BlockStateProperties.HORIZONTAL_FACING,
			context.getPlacementHorizontalFacing() );
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public boolean onBlockActivated(
		BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos,
		PlayerEntity player,
		@Nonnull Hand handIn,
		@Nonnull BlockRayTraceResult hit ) {
		
		player.openContainer( state.getContainer( worldIn, pos ) );
		return true;
	}
	
	@SuppressWarnings( "deprecation" )
	@Override
	public INamedContainerProvider getContainer(
		@Nonnull BlockState state,
		@Nonnull World worldIn,
		@Nonnull BlockPos pos ) {
		
		return new SimpleNamedContainerProvider( ( windowID, playerInventory, playerEntity ) -> new DyeCraftingTableContainer(
			windowID,
			playerInventory,
			IWorldPosCallable.of( worldIn, pos )
		), getNameTextComponent() );
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.DYE_CRAFTING_TABLE, properties, registry_name );
	}
}
