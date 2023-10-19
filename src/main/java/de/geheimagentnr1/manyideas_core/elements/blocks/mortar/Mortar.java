package de.geheimagentnr1.manyideas_core.elements.blocks.mortar;

import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeMemory;
import de.geheimagentnr1.manyideas_core.util.voxel_shapes.VoxelShapeVector;
import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeHolder;
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
	
	@SuppressWarnings( "deprecation" )
	@NotNull
	@Override
	public InteractionResult use(
		@NotNull BlockState state,
		@NotNull Level level,
		@NotNull BlockPos pos,
		@NotNull Player player,
		@NotNull InteractionHand hand,
		@NotNull BlockHitResult hitResult ) {
		
		ItemStack crafting_stack = player.getItemInHand( hand );
		MortarCraftingContainer craftingInventory = new MortarCraftingContainer( crafting_stack );
		Optional<RecipeHolder<GrindingRecipe>> recipe = level.getRecipeManager().getRecipeFor(
			ModRecipeTypesRegisterFactory.GRINDING,
			craftingInventory,
			level
		);
		
		if( recipe.isPresent() ) {
			ItemStack result_stack = recipe.get().value().assemble( craftingInventory, level.registryAccess() );
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
