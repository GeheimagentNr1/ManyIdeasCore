package de.geheimagentnr1.manyideas_core.elements.blocks.cooking_pot;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;


public class CookingPot extends Block implements BlockItemInterface {
	
	
	public static final String registry_name = "cooking_pot";
	
	private static final VoxelShape SHAPE = Block.makeCuboidShape( 4, 0, 4, 12, 4, 12 );
	
	public CookingPot() {
		
		super( Block.Properties.create( Material.ROCK ).hardnessAndResistance( 3.5F ).sound( SoundType.METAL ) );
		setRegistryName( registry_name );
	}
	
	@NotNull
	@Override
	public BlockRenderLayer getRenderLayer() {
		
		return BlockRenderLayer.CUTOUT;
	}
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public VoxelShape getShape( @NotNull BlockState state, @NotNull IBlockReader worldIn, @NotNull BlockPos pos,
		@NotNull ISelectionContext context ) {
		
		return SHAPE;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return getDefaultState().with( BlockStateProperties.HORIZONTAL_FACING,
			context.getPlacementHorizontalFacing() );
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.COOKING_POT, properties, registry_name );
	}
}
