package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.multi_block;

import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


//package-private
@SuppressWarnings( "unused" )
interface MultiBlockCalculater<T> {
	
	
	@NotNull
	Optional<T> calculate( int x, int y, int z, @NotNull BlockPos blockPos );
	
	T getAlternative();
}
