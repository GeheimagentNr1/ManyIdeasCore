package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import net.minecraft.core.BlockPos;


@SuppressWarnings( { "WeakerAccess", "RedundantSuppression" } )
@FunctionalInterface
public interface MultiBlockRunner {
	
	
	//public
	void run( int x, int y, int z, BlockPos blockPos );
}
