package de.geheimagentnr1.manyideascore.elements.blocks.template_blocks.multi_block;

import net.minecraft.util.math.BlockPos;

import java.util.Optional;


//package-private
@SuppressWarnings( "unused" )
interface MultiBlockCalculater<T> {
	
	
	Optional<T> calculate( int x, int y, int z, BlockPos blockPos );
	
	T getAlternative();
}
