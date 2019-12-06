package de.geheimagentnr1.manyideascore.elements.blocks.template_blocks.multi_block;

import net.minecraft.util.math.BlockPos;


@SuppressWarnings( { "WeakerAccess", "RedundantSuppression" } )
@FunctionalInterface
public interface MultiBlockRunner {
	
	
	//public
	void run( int x, int y, int z, BlockPos blockPos );
}
