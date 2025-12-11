package de.geheimagentnr1.manyideas_core.core.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;


public class RegistryHelper {
	
	
	@NotNull
	public static <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(
		@NotNull String registryName,
		@NotNull BlockEntityType.BlockEntitySupplier<T> factory,
		@NotNull Block... blocks ) {
		
		return BlockEntityType.Builder.of( factory, blocks ).build( null );
	}
}
