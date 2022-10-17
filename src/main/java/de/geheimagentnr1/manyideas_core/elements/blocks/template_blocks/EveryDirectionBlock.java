package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.EveryDirectionFacing;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;


public class EveryDirectionBlock extends Block {
	
	
	public EveryDirectionBlock( Properties _properties ) {
		
		super( _properties );
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement( BlockPlaceContext context ) {
		
		return defaultBlockState().setValue(
			ModBlockStateProperties.EVERY_DIRECTION_FACING,
			switch( context.getClickedFace() ) {
				case NORTH -> switch( getNearestAllowedDirection(
					context,
					Direction.UP,
					Direction.WEST,
					Direction.DOWN,
					Direction.EAST
				) ) {
					case UP -> EveryDirectionFacing.SOUTH_UP;
					case WEST -> EveryDirectionFacing.SOUTH_WEST;
					case DOWN -> EveryDirectionFacing.SOUTH_DOWN;
					case EAST -> EveryDirectionFacing.SOUTH_EAST;
					default -> EveryDirectionFacing.SOUTH_UP;
				};
				case EAST -> switch( getNearestAllowedDirection(
					context,
					Direction.UP,
					Direction.NORTH,
					Direction.DOWN,
					Direction.SOUTH
				) ) {
					case UP -> EveryDirectionFacing.WEST_UP;
					case NORTH -> EveryDirectionFacing.WEST_NORTH;
					case DOWN -> EveryDirectionFacing.WEST_DOWN;
					case SOUTH -> EveryDirectionFacing.WEST_SOUTH;
					default -> EveryDirectionFacing.WEST_UP;
				};
				case SOUTH -> switch( getNearestAllowedDirection(
					context,
					Direction.UP,
					Direction.EAST,
					Direction.DOWN,
					Direction.WEST
				) ) {
					case UP -> EveryDirectionFacing.NORTH_UP;
					case EAST -> EveryDirectionFacing.NORTH_EAST;
					case DOWN -> EveryDirectionFacing.NORTH_DOWN;
					case WEST -> EveryDirectionFacing.NORTH_WEST;
					default -> EveryDirectionFacing.NORTH_UP;
				};
				case WEST -> switch( getNearestAllowedDirection(
					context,
					Direction.UP,
					Direction.SOUTH,
					Direction.DOWN,
					Direction.NORTH
				) ) {
					case UP -> EveryDirectionFacing.EAST_UP;
					case SOUTH -> EveryDirectionFacing.EAST_SOUTH;
					case DOWN -> EveryDirectionFacing.EAST_DOWN;
					case NORTH -> EveryDirectionFacing.EAST_NORTH;
					default -> EveryDirectionFacing.EAST_UP;
				};
				case UP -> switch( getNearestAllowedDirection(
					context,
					Direction.NORTH,
					Direction.EAST,
					Direction.SOUTH,
					Direction.WEST
				) ) {
					case NORTH -> EveryDirectionFacing.DOWN_NORTH;
					case EAST -> EveryDirectionFacing.DOWN_EAST;
					case SOUTH -> EveryDirectionFacing.DOWN_SOUTH;
					case WEST -> EveryDirectionFacing.DOWN_WEST;
					default -> EveryDirectionFacing.DOWN_NORTH;
				};
				case DOWN -> switch( getNearestAllowedDirection(
					context,
					Direction.NORTH,
					Direction.EAST,
					Direction.SOUTH,
					Direction.WEST
				) ) {
					case NORTH -> EveryDirectionFacing.UP_NORTH;
					case EAST -> EveryDirectionFacing.UP_EAST;
					case SOUTH -> EveryDirectionFacing.UP_SOUTH;
					case WEST -> EveryDirectionFacing.UP_WEST;
					default -> EveryDirectionFacing.UP_NORTH;
				};
			}
		);
	}
	
	private Direction getNearestAllowedDirection( BlockPlaceContext context, Direction... allowedDirections ) {
		
		List<Direction> allowedDirectionList = List.of( allowedDirections );
		
		return Stream.of( context.getNearestLookingDirections() )
			.filter( allowedDirectionList::contains )
			.findFirst()
			.orElse( null );
	}
	
	@Override
	protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
		
		builder.add( ModBlockStateProperties.EVERY_DIRECTION_FACING );
	}
}
