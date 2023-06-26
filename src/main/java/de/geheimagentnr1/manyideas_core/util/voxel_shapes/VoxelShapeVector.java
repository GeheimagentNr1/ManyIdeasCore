package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class VoxelShapeVector {
	
	
	//package-private
	final double x1;
	
	//package-private
	final double y1;
	
	//package-private
	final double z1;
	
	//package-private
	final double x2;
	
	//package-private
	final double y2;
	
	//package-private
	final double z2;
	
	private VoxelShapeVector( double _x1, double _y1, double _z1, double _x2, double _y2, double _z2 ) {
		
		x1 = _x1;
		y1 = _y1;
		z1 = _z1;
		x2 = _x2;
		y2 = _y2;
		z2 = _z2;
	}
	
	@NotNull
	public static VoxelShapeVector create( double _x1, double _y1, double _z1, double _x2, double _y2, double _z2 ) {
		
		return new VoxelShapeVector( _x1, _y1, _z1, _x2, _y2, _z2 );
	}
	
	@Override
	public boolean equals( Object obj ) {
		
		if( this == obj ) {
			return true;
		}
		if( obj == null || getClass() != obj.getClass() ) {
			return false;
		}
		VoxelShapeVector voxelShapeVector = (VoxelShapeVector)obj;
		return Double.compare( voxelShapeVector.x1, x1 ) == 0 && Double.compare( voxelShapeVector.y1, y1 ) == 0 &&
			Double.compare( voxelShapeVector.z1, z1 ) == 0 && Double.compare( voxelShapeVector.x2, x2 ) == 0 &&
			Double.compare( voxelShapeVector.y2, y2 ) == 0 && Double.compare( voxelShapeVector.z2, z2 ) == 0;
	}
	
	@SuppressWarnings( "ObjectInstantiationInEqualsHashCode" )
	@Override
	public int hashCode() {
		
		return Objects.hash( x1, y1, z1, x2, y2, z2 );
	}
	
	@Override
	public String toString() {
		
		return "VoxelShapeVector{" +
			"x1=" + x1 +
			", y1=" + y1 +
			", z1=" + z1 +
			", x2=" + x2 +
			", y2=" + y2 +
			", z2=" + z2 +
			'}';
	}
}
