package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.BlockRenderTypeInterface;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

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
		
		super( BlockBehaviour.Properties.of()
			.mapColor( MapColor.QUARTZ )
			.strength( 0.8F )
			.requiresCorrectToolForDrops()
			.sound( SoundType.STONE ) );
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
		
		return SHAPES.getShapeFromHorizontalFacing( state.getValue( BlockStateProperties.HORIZONTAL_FACING ) );
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
		
		ItemStack crafting_stack = player.getItemInHand( hand );
		MortarCraftingContainer craftingInventory = new MortarCraftingContainer( crafting_stack );
		Optional<GrindingRecipe> recipe = level.getRecipeManager().getRecipeFor(
			RecipeTypes.GRINDING,
			craftingInventory,
			level
		);
		
		if( recipe.isPresent() ) {
			ItemStack result_stack = recipe.get().assemble( craftingInventory, level.registryAccess() );
			crafting_stack.shrink( 1 );
			if( !player.addItem( result_stack ) ) {
				player.drop( result_stack, false );
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockPlaceContext context ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getHorizontalDirection()
		);
	}
	
	@Override
	protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.MORTAR, _properties, registry_name );
	}
}
