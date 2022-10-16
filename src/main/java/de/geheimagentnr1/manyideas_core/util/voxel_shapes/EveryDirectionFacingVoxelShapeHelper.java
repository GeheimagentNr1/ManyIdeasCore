package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.EveryDirectionFacing;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;


public class EveryDirectionFacingVoxelShapeHelper {
	
	
	public static VoxelShapeVector[] vectorsToDownNorthVectors(
		VoxelShapeVector[] vectors,
		EveryDirectionFacing facing ) {
		
		List<double[][]> turn_matrixes = new ArrayList<>();
		switch( facing ) {
			case NORTH_UP:
				turn_matrixes.add( createXturnMatrix( 270 ) );
				break;
			case NORTH_EAST:
				turn_matrixes.add( createXturnMatrix( 270 ) );
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			case NORTH_DOWN:
				turn_matrixes.add( createXturnMatrix( 270 ) );
				turn_matrixes.add( createYturnMatrix( 180 ) );
				break;
			case NORTH_WEST:
				turn_matrixes.add( createXturnMatrix( 270 ) );
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			
			case EAST_UP:
				turn_matrixes.add( createZturnMatrix( 270 ) );
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			case EAST_NORTH:
				turn_matrixes.add( createZturnMatrix( 270 ) );
				break;
			case EAST_DOWN:
				turn_matrixes.add( createZturnMatrix( 270 ) );
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			case EAST_SOUTH:
				turn_matrixes.add( createZturnMatrix( 270 ) );
				turn_matrixes.add( createYturnMatrix( 180 ) );
				break;
			
			case SOUTH_UP:
				turn_matrixes.add( createXturnMatrix( 90 ) );
				turn_matrixes.add( createYturnMatrix( 180 ) );
				break;
			case SOUTH_WEST:
				turn_matrixes.add( createXturnMatrix( 90 ) );
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			case SOUTH_DOWN:
				turn_matrixes.add( createXturnMatrix( 90 ) );
				break;
			case SOUTH_EAST:
				turn_matrixes.add( createXturnMatrix( 90 ) );
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			
			case WEST_UP:
				turn_matrixes.add( createZturnMatrix( 90 ) );
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			case WEST_SOUTH:
				turn_matrixes.add( createZturnMatrix( 90 ) );
				turn_matrixes.add( createYturnMatrix( 180 ) );
				break;
			case WEST_DOWN:
				turn_matrixes.add( createZturnMatrix( 90 ) );
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			case WEST_NORTH:
				turn_matrixes.add( createZturnMatrix( 90 ) );
				break;
			
			case UP_NORTH:
				turn_matrixes.add( createZturnMatrix( 180 ) );
				break;
			case UP_EAST:
				turn_matrixes.add( createXturnMatrix( 180 ) );
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			case UP_SOUTH:
				turn_matrixes.add( createXturnMatrix( 180 ) );
				break;
			case UP_WEST:
				turn_matrixes.add( createXturnMatrix( 180 ) );
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			
			case DOWN_NORTH:
				return vectors;
			case DOWN_EAST:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			case DOWN_SOUTH:
				turn_matrixes.add( createYturnMatrix( 180 ) );
				break;
			case DOWN_WEST:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			default:
				turn_matrixes.add( createYturnMatrix( 0 ) );
				break;
		}
		for( double[][] turn_matrix : turn_matrixes ) {
			vectors = turnVoxelShapeVectors( vectors, turn_matrix );
		}
		return vectors;
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
	
	private static double[][] createZturnMatrix( double degree ) {
		
		return new double[][] {
			{ cos( degree ), -sin( degree ), 0 },
			{ sin( degree ), cos( degree ), 0 },
			{ 0, 0, 1 }
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
	
	public static VoxelShapeVector[] calculateVectors( VoxelShapeVector[] vectors, EveryDirectionFacing facing ) {
		
		List<double[][]> turn_matrixes = new ArrayList<>();
		switch( facing ) {
			case NORTH_UP:
				turn_matrixes.add( createXturnMatrix( 90 ) );
				break;
			case NORTH_EAST:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				turn_matrixes.add( createXturnMatrix( 90 ) );
				break;
			case NORTH_DOWN:
				turn_matrixes.add( createYturnMatrix( 180 ) );
				turn_matrixes.add( createXturnMatrix( 90 ) );
				break;
			case NORTH_WEST:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				turn_matrixes.add( createXturnMatrix( 90 ) );
				break;
			
			case EAST_UP:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				turn_matrixes.add( createZturnMatrix( 90 ) );
				break;
			case EAST_NORTH:
				turn_matrixes.add( createZturnMatrix( 90 ) );
				break;
			case EAST_DOWN:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				turn_matrixes.add( createZturnMatrix( 90 ) );
				break;
			case EAST_SOUTH:
				turn_matrixes.add( createYturnMatrix( 180 ) );
				turn_matrixes.add( createZturnMatrix( 90 ) );
				break;
			
			case SOUTH_UP:
				turn_matrixes.add( createYturnMatrix( 180 ) );
				turn_matrixes.add( createXturnMatrix( 270 ) );
				break;
			case SOUTH_WEST:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				turn_matrixes.add( createXturnMatrix( 270 ) );
				break;
			case SOUTH_DOWN:
				turn_matrixes.add( createXturnMatrix( 270 ) );
				break;
			case SOUTH_EAST:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				turn_matrixes.add( createXturnMatrix( 270 ) );
				break;
			
			case WEST_UP:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				turn_matrixes.add( createZturnMatrix( 270 ) );
				break;
			case WEST_SOUTH:
				turn_matrixes.add( createYturnMatrix( 180 ) );
				turn_matrixes.add( createZturnMatrix( 270 ) );
				break;
			case WEST_DOWN:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				turn_matrixes.add( createZturnMatrix( 270 ) );
				break;
			case WEST_NORTH:
				turn_matrixes.add( createZturnMatrix( 270 ) );
				break;
			
			case UP_NORTH:
				turn_matrixes.add( createZturnMatrix( 180 ) );
				break;
			case UP_EAST:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				turn_matrixes.add( createXturnMatrix( 180 ) );
				break;
			case UP_SOUTH:
				turn_matrixes.add( createXturnMatrix( 180 ) );
				break;
			case UP_WEST:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				turn_matrixes.add( createXturnMatrix( 180 ) );
				break;
			
			case DOWN_NORTH:
				return vectors;
			case DOWN_EAST:
				turn_matrixes.add( createYturnMatrix( 270 ) );
				break;
			case DOWN_SOUTH:
				turn_matrixes.add( createYturnMatrix( 180 ) );
				break;
			case DOWN_WEST:
				turn_matrixes.add( createYturnMatrix( 90 ) );
				break;
			default:
				turn_matrixes.add( createYturnMatrix( 0 ) );
				break;
		}
		for( double[][] turn_matrix : turn_matrixes ) {
			vectors = turnVoxelShapeVectors( vectors, turn_matrix );
		}
		return vectors;
	}
	
	@SuppressWarnings( "WeakerAccess" )
	public static VoxelShape vectorsToVoxelShape( VoxelShapeVector[] vectors ) {
		
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
