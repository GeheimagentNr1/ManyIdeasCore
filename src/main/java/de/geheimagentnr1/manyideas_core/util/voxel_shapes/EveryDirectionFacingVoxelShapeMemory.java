package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.EveryDirectionFacing;
import net.minecraft.world.phys.shapes.VoxelShape;


@SuppressWarnings( "unused" )
public class EveryDirectionFacingVoxelShapeMemory {
	
	
	private final VoxelShape north_up;
	
	private final VoxelShape north_east;
	
	private final VoxelShape north_down;
	
	private final VoxelShape north_west;
	
	private final VoxelShape east_up;
	
	private final VoxelShape east_north;
	
	private final VoxelShape east_down;
	
	private final VoxelShape east_south;
	
	private final VoxelShape south_up;
	
	private final VoxelShape south_west;
	
	private final VoxelShape south_down;
	
	private final VoxelShape south_east;
	
	private final VoxelShape west_up;
	
	private final VoxelShape west_south;
	
	private final VoxelShape west_down;
	
	private final VoxelShape west_north;
	
	private final VoxelShape up_north;
	
	private final VoxelShape up_east;
	
	private final VoxelShape up_south;
	
	private final VoxelShape up_west;
	
	private final VoxelShape down_north;
	
	private final VoxelShape down_east;
	
	private final VoxelShape down_south;
	
	private final VoxelShape down_west;
	
	public EveryDirectionFacingVoxelShapeMemory(
		VoxelShape north_up,
		VoxelShape north_east,
		VoxelShape north_down,
		VoxelShape north_west,
		VoxelShape east_up,
		VoxelShape east_north,
		VoxelShape east_down,
		VoxelShape east_south,
		VoxelShape south_up,
		VoxelShape south_west,
		VoxelShape south_down,
		VoxelShape south_east,
		VoxelShape west_up,
		VoxelShape west_south,
		VoxelShape west_down,
		VoxelShape west_north,
		VoxelShape up_north,
		VoxelShape up_east,
		VoxelShape up_south,
		VoxelShape up_west,
		VoxelShape down_north,
		VoxelShape down_east,
		VoxelShape down_south,
		VoxelShape down_west ) {
		
		this.north_up = north_up;
		this.north_east = north_east;
		this.north_down = north_down;
		this.north_west = north_west;
		this.east_up = east_up;
		this.east_north = east_north;
		this.east_down = east_down;
		this.east_south = east_south;
		this.south_up = south_up;
		this.south_west = south_west;
		this.south_down = south_down;
		this.south_east = south_east;
		this.west_up = west_up;
		this.west_south = west_south;
		this.west_down = west_down;
		this.west_north = west_north;
		this.up_north = up_north;
		this.up_east = up_east;
		this.up_south = up_south;
		this.up_west = up_west;
		this.down_north = down_north;
		this.down_east = down_east;
		this.down_south = down_south;
		this.down_west = down_west;
	}
	
	@SuppressWarnings( { "DuplicatedCode", "VariableArgumentMethod" } )
	public static EveryDirectionFacingVoxelShapeMemory createEveryDirectionVoxelShapes(
		EveryDirectionFacing facing,
		VoxelShapeVector... vectors ) {
		
		VoxelShapeVector[] down_north_vectors = EveryDirectionFacingVoxelShapeHelper.vectorsToDownNorthVectors(
			vectors,
			facing
		);
		return new EveryDirectionFacingVoxelShapeMemory(
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.NORTH_UP
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.NORTH_EAST
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.NORTH_DOWN
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.NORTH_WEST
			) ),
			
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.EAST_UP
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.EAST_NORTH
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.EAST_DOWN
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.EAST_SOUTH
			) ),
			
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.SOUTH_UP
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.SOUTH_WEST
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.SOUTH_DOWN
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.SOUTH_EAST
			) ),
			
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.WEST_UP
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.WEST_SOUTH
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.WEST_DOWN
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.WEST_NORTH
			) ),
			
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.UP_NORTH
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.UP_EAST
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.UP_SOUTH
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.UP_WEST
			) ),
			
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.DOWN_NORTH
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.DOWN_EAST
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.DOWN_SOUTH
			) ),
			EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
				down_north_vectors,
				EveryDirectionFacing.DOWN_WEST
			) )
		);
	}
	
	@SuppressWarnings( "VariableArgumentMethod" )
	public static VoxelShape createVoxelShape( VoxelShapeVector... vectors ) {
		
		return EveryDirectionFacingVoxelShapeHelper.vectorsToVoxelShape( vectors );
	}
	
	public VoxelShape getShapeFromEveryDirectionFacing( EveryDirectionFacing facing ) {
		
		return switch( facing ) {
			case NORTH_UP -> north_up;
			case NORTH_EAST -> north_east;
			case NORTH_DOWN -> north_down;
			case NORTH_WEST -> north_west;
			case EAST_UP -> east_up;
			case EAST_NORTH -> east_north;
			case EAST_DOWN -> east_down;
			case EAST_SOUTH -> east_south;
			case SOUTH_UP -> south_up;
			case SOUTH_WEST -> south_west;
			case SOUTH_DOWN -> south_down;
			case SOUTH_EAST -> south_east;
			case WEST_UP -> west_up;
			case WEST_SOUTH -> west_south;
			case WEST_DOWN -> west_down;
			case WEST_NORTH -> west_north;
			case UP_NORTH -> up_north;
			case UP_EAST -> up_east;
			case UP_SOUTH -> up_south;
			case UP_WEST -> up_west;
			case DOWN_NORTH -> down_north;
			case DOWN_EAST -> down_east;
			case DOWN_SOUTH -> down_south;
			case DOWN_WEST -> down_west;
		};
	}
}
