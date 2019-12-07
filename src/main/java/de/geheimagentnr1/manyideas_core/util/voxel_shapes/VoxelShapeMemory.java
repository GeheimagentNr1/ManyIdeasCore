package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;


@SuppressWarnings( "unused" )
public class VoxelShapeMemory {
	
	
	private final VoxelShape north;
	
	private final VoxelShape east;
	
	private final VoxelShape south;
	
	private final VoxelShape west;
	
	private final VoxelShape up;
	
	private final VoxelShape down;
	
	private VoxelShapeMemory( VoxelShape _north, VoxelShape _east, VoxelShape _south, VoxelShape _west, VoxelShape _up,
		VoxelShape _down ) {
		
		north = _north;
		east = _east;
		south = _south;
		west = _west;
		up = _up;
		down = _down;
	}
	
	@SuppressWarnings( "VariableArgumentMethod" )
	public static VoxelShapeMemory createHorizontalAxisVoxelShapes( Direction facing, VoxelShapeVector... vectors ) {
		
		VoxelShapeVector[] north_vectors = VoxelShapeHelper.vectorsToNorthVectors( vectors, facing );
		VoxelShape north = VoxelShapeHelper.vectorsToVoxelShape( north_vectors );
		VoxelShape east = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.eastVectors( north_vectors ) );
		return new VoxelShapeMemory( north, east, null, null, null, null );
	}
	
	@SuppressWarnings( { "DuplicatedCode", "VariableArgumentMethod" } )
	public static VoxelShapeMemory createHorizontalVoxelShapes( Direction facing, VoxelShapeVector... vectors ) {
		
		VoxelShapeVector[] north_vectors = VoxelShapeHelper.vectorsToNorthVectors( vectors, facing );
		VoxelShape north = VoxelShapeHelper.vectorsToVoxelShape( north_vectors );
		VoxelShape east = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.eastVectors( north_vectors ) );
		VoxelShape south = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.southVectors( north_vectors ) );
		VoxelShape west = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.westVectors( north_vectors ) );
		return new VoxelShapeMemory( north, east, south, west, null, null );
	}
	
	@SuppressWarnings( { "DuplicatedCode", "VariableArgumentMethod" } )
	public static VoxelShapeMemory createVoxelShapes( Direction facing, VoxelShapeVector... vectors ) {
		
		VoxelShapeVector[] north_vectors = VoxelShapeHelper.vectorsToNorthVectors( vectors, facing );
		VoxelShape north = VoxelShapeHelper.vectorsToVoxelShape( north_vectors );
		VoxelShape east = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.eastVectors( north_vectors ) );
		VoxelShape south = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.southVectors( north_vectors ) );
		VoxelShape west = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.westVectors( north_vectors ) );
		VoxelShape up = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.upVectors( north_vectors ) );
		VoxelShape down = VoxelShapeHelper.vectorsToVoxelShape( VoxelShapeHelper.downVectors( north_vectors ) );
		return new VoxelShapeMemory( north, east, south, west, up, down );
	}
	
	@SuppressWarnings( "VariableArgumentMethod" )
	public static VoxelShape createVoxelShape( VoxelShapeVector... vectors ) {
		
		return VoxelShapeHelper.vectorsToVoxelShape( vectors );
	}
	
	
	public VoxelShape getShapeFromHorizontalAxis( Direction.Axis horizontal_axis ) {
		
		switch( horizontal_axis ) {
			case Z:
				return north;
			case X:
				return east;
		}
		return VoxelShapes.fullCube();
	}
	
	public VoxelShape getShapeFromHorizontalFacing( Direction horizontal_facing ) {
		
		switch( horizontal_facing ) {
			case NORTH:
				return north;
			case EAST:
				return east;
			case SOUTH:
				return south;
			case WEST:
				return west;
		}
		return VoxelShapes.fullCube();
	}
	
	public VoxelShape getShapeFromFacing( Direction facing ) {
		
		switch( facing ) {
			case NORTH:
				return north;
			case EAST:
				return east;
			case SOUTH:
				return south;
			case WEST:
				return west;
			case UP:
				return up;
			case DOWN:
				return down;
		}
		return VoxelShapes.fullCube();
	}
}
