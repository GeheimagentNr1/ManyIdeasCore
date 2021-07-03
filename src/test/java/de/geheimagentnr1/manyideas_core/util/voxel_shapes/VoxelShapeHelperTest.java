package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import net.minecraft.util.Direction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//package-private
class VoxelShapeHelperTest {
	
	
	//package-private
	@Test
	void test() {
		
		Direction[] directions = Direction.values();
		VoxelShapeVector[] vectors = new VoxelShapeVector[] {
			VoxelShapeVector.create( 0, 14, 0, 0, 16, 4 ),
			VoxelShapeVector.create( 16, 14, 0, 12, 16, 0 ),
			VoxelShapeVector.create( 16, 14, 16, 16, 16, 12 ),
			VoxelShapeVector.create( 0, 14, 16, 4, 16, 16 ),
			VoxelShapeVector.create( 0, 16, 14, 0, 12, 16 ),
			VoxelShapeVector.create( 0, 0, 2, 0, 4, 0 )
		};
		for( int i = 0; i < directions.length && i < vectors.length; i++ ) {
			Assertions.assertEquals( vectors[0], VoxelShapeHelper.vectorsToNorthVectors(
				new VoxelShapeVector[]{ vectors[i] }, directions[i] )[0], "north not equal" );
		}
		VoxelShapeVector[] north_vectors = new VoxelShapeVector[] { VoxelShapeVector.create( 0, 14, 0, 0, 16, 4 ) };
		for( int i = 0; i < directions.length && i < vectors.length; i++ ) {
			VoxelShapeVector[] directionVectors;
			switch( directions[i] ) {
				case NORTH:
					directionVectors = north_vectors;
					break;
				case EAST:
					directionVectors = VoxelShapeHelper.eastVectors( north_vectors );
					break;
				case SOUTH:
					directionVectors = VoxelShapeHelper.southVectors( north_vectors );
					break;
				case WEST:
					directionVectors = VoxelShapeHelper.westVectors( north_vectors );
					break;
				case UP:
					directionVectors = VoxelShapeHelper.upVectors( north_vectors );
					break;
				case DOWN:
					directionVectors = VoxelShapeHelper.downVectors( north_vectors );
					break;
				default:
					throw new IllegalStateException( "Invalid Direction" );
			}
			Assertions.assertEquals( vectors[i], directionVectors[0], "north not equal" );
		}
	}
}
