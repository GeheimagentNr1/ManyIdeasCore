package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;


public class Mortar extends Block implements BlockItemInterface, BlockRenderTypeInterface {
	
	
	public static final String registry_name = "mortar";
	
	private static final VoxelShapeMemory SHAPES = VoxelShapeMemory.createHorizontalVoxelShapes(
		Direction.SOUTH,
		VoxelShapeVector.create( 1, 0, 2, 6, 6, 7 )
	);
	
	public Mortar() {
		
		super( Block.Properties.create( Material.ROCK )
			.hardnessAndResistance( 0.8F )
			.harvestTool( ToolType.PICKAXE )
			.harvestLevel( 0 )
			.sound( SoundType.STONE ) );
		setRegistryName( registry_name );
	}
	
	@Override
	public RenderType getRenderType() {
		
		return RenderType.getCutout();
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public VoxelShape getShape(
		BlockState state,
		@Nonnull IBlockReader worldIn,
		@Nonnull BlockPos pos,
		@Nonnull ISelectionContext context ) {
		
		return SHAPES.getShapeFromHorizontalFacing( state.get( BlockStateProperties.HORIZONTAL_FACING ) );
	}
	
	@SuppressWarnings( "deprecation" )
	@Nonnull
	@Override
	public ActionResultType onBlockActivated(
		@Nonnull BlockState state,
		World worldIn,
		@Nonnull BlockPos pos,
		PlayerEntity player,
		@Nonnull Hand handIn,
		@Nonnull BlockRayTraceResult hit ) {
		
		ItemStack crafting_stack = player.getHeldItem( handIn );
		MortarCraftingInventory craftingInventory = new MortarCraftingInventory( crafting_stack );
		Optional<GrindingRecipe> recipe = worldIn.getRecipeManager().getRecipe(
			RecipeTypes.GRINDING,
			craftingInventory,
			worldIn
		);
		
		if( recipe.isPresent() ) {
			ItemStack result_stack = recipe.get().getCraftingResult( craftingInventory );
			crafting_stack.shrink( 1 );
			if( !player.addItemStackToInventory( result_stack ) ) {
				player.dropItem( result_stack, false );
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockItemUseContext context ) {
		
		return getDefaultState().with(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getPlacementHorizontalFacing()
		);
	}
	
	@Override
	protected void fillStateContainer( StateContainer.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.MORTAR, properties, registry_name );
	}
}
