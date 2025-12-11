package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlockItemInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;


public class Mortar extends Block implements BlockItemInterface {
	
	
	@NotNull
	public static final String registry_name = "mortar";
	
	@NotNull
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
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public VoxelShape getShape(
		@NotNull BlockState state,
		@NotNull BlockGetter level,
		@NotNull BlockPos pos,
		@NotNull CollisionContext context ) {
		
		return SHAPES.getShapeFromHorizontalFacing( state.getValue( BlockStateProperties.HORIZONTAL_FACING ) );
	}
	
	@Override
	protected ItemInteractionResult useItemOn(
		ItemStack pStack,
		BlockState pState,
		Level pLevel,
		BlockPos pPos,
		Player pPlayer,
		InteractionHand pHand,
		BlockHitResult pHitResult ) {
		
		SingleRecipeInput craftingInventory = new SingleRecipeInput( pStack );
		Optional<RecipeHolder<GrindingRecipe>> recipe = pLevel.getRecipeManager().getRecipeFor(
			ModRecipeTypesRegisterFactory.GRINDING,
			craftingInventory,
			pLevel
		);
		
		if( recipe.isPresent() ) {
			ItemStack result_stack = recipe.get().value().assemble( craftingInventory, pLevel.registryAccess() );
			pStack.shrink( 1 );
			if( !pPlayer.addItem( result_stack ) ) {
				pPlayer.drop( result_stack, false );
			}
			return ItemInteractionResult.SUCCESS;
		}
		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( @NotNull BlockPlaceContext context ) {
		
		return defaultBlockState().setValue(
			BlockStateProperties.HORIZONTAL_FACING,
			context.getHorizontalDirection()
		);
	}
	
	@Override
	protected void createBlockStateDefinition( @NotNull StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( BlockStateProperties.HORIZONTAL_FACING );
	}
}
