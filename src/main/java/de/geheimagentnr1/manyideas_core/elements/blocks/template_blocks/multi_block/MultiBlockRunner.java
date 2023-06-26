package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings( { "WeakerAccess", "RedundantSuppression" } )
@FunctionalInterface
public interface MultiBlockRunner {
	
	
	//public
	void run( int x, int y, int z, @NotNull BlockPos blockPos );
}
