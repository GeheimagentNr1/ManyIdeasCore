package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class VoxelShapeHelper {
	
	
	@NotNull
	public static VoxelShapeVector[] vectorsToNorthVectors(
		@NotNull VoxelShapeVector[] vectors,
		@NotNull Direction facing ) {
		
		double[][] turn_matrix;
		switch( facing ) {
			case NORTH -> {
				return vectors;
			}
			case EAST -> turn_matrix = createYturnMatrix( 90 );
			case SOUTH -> turn_matrix = createYturnMatrix( 180 );
			case WEST -> turn_matrix = createYturnMatrix( 270 );
			case UP -> turn_matrix = createXturnMatrix( -90 );
			case DOWN -> turn_matrix = createXturnMatrix( 90 );
			default -> turn_matrix = createYturnMatrix( 0 );
		}
		return turnVoxelShapeVectors( vectors, turn_matrix );
	}
	
	private static double[][] createXturnMatrix( double degree ) {
		
		return new double[][] {
			{ 1, 0, 0 },
			{ 0, cos( degree ), -sin( degree ) },
			{ 0, sin( degree ), cos( degree ) }
		};
	}
	
	private static double[][] createYturnMatrix( double degree ) {
		
		return new double[][] {
			{ cos( degree ), 0, sin( degree ) },
			{ 0, 1, 0 },
			{ -sin( degree ), 0, cos( degree ) }
		};
	}
	
	private static double cos( double degree ) {
		
		degree = ( degree + ( degree < 0 ? 360 : 0 ) ) % 360.0;
		return switch( (int)degree ) {
			case 0 -> 1;
			case 90 -> 0;
			case 180 -> -1;
			case 270 -> 0;
			default -> 1;
		};
	}
	
	private static double sin( double degree ) {
		
		degree = ( degree + ( degree < 0 ? 360 : 0 ) ) % 360.0;
		return switch( (int)degree ) {
			case 0 -> 0;
			case 90 -> 1;
			case 180 -> 0;
			case 270 -> -1;
			default -> 1;
		};
	}
	
	@NotNull
	private static VoxelShapeVector[] turnVoxelShapeVectors(
		@NotNull VoxelShapeVector[] vectors,
		double[][] turn_matrix ) {
		
		VoxelShapeVector[] turned_vectors = new VoxelShapeVector[vectors.length];
		for( int i = 0; i < vectors.length; i++ ) {
			turned_vectors[i] = turnVoxelShapeVector( vectors[i], turn_matrix );
		}
		return turned_vectors;
	}
	
	@NotNull
	private static VoxelShapeVector turnVoxelShapeVector( @NotNull VoxelShapeVector vector, double[][] turn_matrix ) {
		
		double[] vector1 = new double[] { vector.x1, vector.y1, vector.z1 };
		double[] vector2 = new double[] { vector.x2, vector.y2, vector.z2 };
		vector1 = turnVector( vector1, turn_matrix );
		vector2 = turnVector( vector2, turn_matrix );
		return VoxelShapeVector.create( vector1[0], vector1[1], vector1[2], vector2[0], vector2[1], vector2[2] );
	}
	
	private static double[] turnVector( double[] vector, double[][] turn_matrix ) {
		
		double vx = vector[0] - 8.0;
		double vy = vector[1] - 8.0;
		double vz = vector[2] - 8.0;
		double x = vx * turn_matrix[0][0] + vy * turn_matrix[0][1] + vz * turn_matrix[0][2];
		double y = vx * turn_matrix[1][0] + vy * turn_matrix[1][1] + vz * turn_matrix[1][2];
		double z = vx * turn_matrix[2][0] + vy * turn_matrix[2][1] + vz * turn_matrix[2][2];
		x += 8.0;
		y += 8.0;
		z += 8.0;
		return new double[] { x, y, z };
	}
	
	@NotNull
	public static VoxelShapeVector[] eastVectors( @NotNull VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createYturnMatrix( -90 ) );
	}
	
	@NotNull
	public static VoxelShapeVector[] southVectors( @NotNull VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createYturnMatrix( -180 ) );
	}
	
	@NotNull
	public static VoxelShapeVector[] westVectors( @NotNull VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createYturnMatrix( -270 ) );
	}
	
	@NotNull
	public static VoxelShapeVector[] upVectors( @NotNull VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createXturnMatrix( 90 ) );
	}
	
	@NotNull
	public static VoxelShapeVector[] downVectors( @NotNull VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createXturnMatrix( -90 ) );
	}
	
	@SuppressWarnings( "WeakerAccess" )
	@NotNull
	public static VoxelShape vectorsToVoxelShape( @NotNull VoxelShapeVector[] vectors ) {
		
		ArrayList<VoxelShape> shapes = new ArrayList<>();
		for( VoxelShapeVector vector : vectors ) {
			shapes.add( Block.box(
				Math.min( vector.x1, vector.x2 ),
				Math.min( vector.y1, vector.y2 ),
				Math.min( vector.z1, vector.z2 ),
				Math.max( vector.x1, vector.x2 ),
				Math.max( vector.y1, vector.y2 ),
				Math.max( vector.z1, vector.z2 )
			) );
		}
		return Shapes.or( Shapes.empty(), shapes.toArray( new VoxelShape[0] ) );
	}
}
