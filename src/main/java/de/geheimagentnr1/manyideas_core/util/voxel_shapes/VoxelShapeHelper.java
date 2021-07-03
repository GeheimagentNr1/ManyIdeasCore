package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.ArrayList;


public class VoxelShapeHelper {
	
	
	public static VoxelShapeVector[] vectorsToNorthVectors( VoxelShapeVector[] vectors, Direction facing ) {
		
		double[][] turn_matrix;
		switch( facing ) {
			case NORTH:
				return vectors;
			case EAST:
				turn_matrix = createYturnMatrix( 90 );
				break;
			case SOUTH:
				turn_matrix = createYturnMatrix( 180 );
				break;
			case WEST:
				turn_matrix = createYturnMatrix( 270 );
				break;
			case UP:
				turn_matrix = createXturnMatrix( -90 );
				break;
			case DOWN:
				turn_matrix = createXturnMatrix( 90 );
				break;
			default:
				turn_matrix = createYturnMatrix( 0 );
				break;
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
		switch( (int)degree ) {
			case 0:
				return 1;
			case 90:
				return 0;
			case 180:
				return -1;
			case 270:
				return 0;
			default:
				return 1;
		}
	}
	
	private static double sin( double degree ) {
		
		degree = ( degree + ( degree < 0 ? 360 : 0 ) ) % 360.0;
		switch( (int)degree ) {
			case 0:
				return 0;
			case 90:
				return 1;
			case 180:
				return 0;
			case 270:
				return -1;
			default:
				return 1;
		}
	}
	
	private static VoxelShapeVector[] turnVoxelShapeVectors( VoxelShapeVector[] vectors, double[][] turn_matrix ) {
		
		VoxelShapeVector[] turned_vectors = new VoxelShapeVector[vectors.length];
		for( int i = 0; i < vectors.length; i++ ) {
			turned_vectors[i] = turnVoxelShapeVector( vectors[i], turn_matrix );
		}
		return turned_vectors;
	}
	
	private static VoxelShapeVector turnVoxelShapeVector( VoxelShapeVector vector, double[][] turn_matrix ) {
		
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
	
	public static VoxelShapeVector[] eastVectors( VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createYturnMatrix( -90 ) );
	}
	
	public static VoxelShapeVector[] southVectors( VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createYturnMatrix( -180 ) );
	}
	
	public static VoxelShapeVector[] westVectors( VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createYturnMatrix( -270 ) );
	}
	
	public static VoxelShapeVector[] upVectors( VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createXturnMatrix( 90 ) );
	}
	
	public static VoxelShapeVector[] downVectors( VoxelShapeVector[] vectors ) {
		
		return turnVoxelShapeVectors( vectors, createXturnMatrix( -90 ) );
	}
	
	@SuppressWarnings( "WeakerAccess" )
	public static VoxelShape vectorsToVoxelShape( VoxelShapeVector[] vectors ) {
		
		ArrayList<VoxelShape> shapes = new ArrayList<>();
		for( VoxelShapeVector vector : vectors ) {
			shapes.add( Block.makeCuboidShape( vector.x1, vector.y1, vector.z1, vector.x2, vector.y2, vector.z2 ) );
		}
		return VoxelShapes.or( VoxelShapes.empty(), shapes.toArray( new VoxelShape[0] ) );
	}
}
