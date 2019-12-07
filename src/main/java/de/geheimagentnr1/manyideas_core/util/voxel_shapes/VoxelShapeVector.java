package de.geheimagentnr1.manyideas_core.util.voxel_shapes;

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
	
	public static VoxelShapeVector create( double _x1, double _y1, double _z1, double _x2, double _y2, double _z2 ) {
		
		return new VoxelShapeVector( _x1, _y1, _z1, _x2, _y2, _z2 );
	}
}
