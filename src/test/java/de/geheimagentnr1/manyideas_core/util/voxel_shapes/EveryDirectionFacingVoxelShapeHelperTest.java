package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.EveryDirectionFacing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//package-private
class EveryDirectionFacingVoxelShapeHelperTest {
	
	
	//package-private
	@Test
	void test() {
		
		VoxelShapeVector vector = VoxelShapeVector.create( 5, 0, 1, 11, 5, 13 );
		VoxelShapeVector[] vectors = new VoxelShapeVector[] {
			vector
		};
		
		for( EveryDirectionFacing everyDirectionFacing : EveryDirectionFacing.values() ) {
			Assertions.assertEquals(
				vector,
				EveryDirectionFacingVoxelShapeHelper.vectorsToDownNorthVectors( EveryDirectionFacingVoxelShapeHelper.calculateVectors(
					vectors,
					everyDirectionFacing
				), everyDirectionFacing )[0],
				"Failed for " + everyDirectionFacing
			);
		}
	}
}