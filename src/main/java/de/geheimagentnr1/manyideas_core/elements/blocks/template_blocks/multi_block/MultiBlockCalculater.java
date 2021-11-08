package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import net.minecraft.core.BlockPos;

import java.util.Optional;


//package-private
@SuppressWarnings( "unused" )
interface MultiBlockCalculater<T> {
	
	
	Optional<T> calculate( int x, int y, int z, BlockPos blockPos );
	
	T getAlternative();
}
