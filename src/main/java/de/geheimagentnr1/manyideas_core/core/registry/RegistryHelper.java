package de.geheimagentnr1.manyideas_core.core.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Supplier;


public class RegistryHelper {
	
	
	//Key of the registry entry that is currently created by a RegistryEntry supplier
	@NotNull
	private static final ThreadLocal<ResourceKey<?>> CURRENT_KEY = new ThreadLocal<>();
	
	@NotNull
	public static <T extends BlockEntity> BlockEntityType<T> buildBlockEntity(
		@NotNull String registryName,
		@NotNull BlockEntityType.BlockEntitySupplier<T> factory,
		@NotNull Block... blocks ) {
		
		return new BlockEntityType<>( factory, Set.of( blocks ) );
	}
	
	@NotNull
	static <T> T withKey( @NotNull ResourceKey<T> key, @NotNull Supplier<T> supplier ) {
		
		ResourceKey<?> previous = CURRENT_KEY.get();
		CURRENT_KEY.set( key );
		try {
			return supplier.get();
		} finally {
			CURRENT_KEY.set( previous );
		}
	}
	
	@Nullable
	@SuppressWarnings( "unchecked" )
	private static <T> ResourceKey<T> currentKey( @NotNull ResourceKey<? extends net.minecraft.core.Registry<T>> registry ) {
		
		ResourceKey<?> key = CURRENT_KEY.get();
		return key != null && key.isFor( registry ) ? (ResourceKey<T>)key : null;
	}
	
	//Sets the block id, if the block is created by a RegistryEntry supplier
	@NotNull
	public static BlockBehaviour.Properties withBlockId( @NotNull BlockBehaviour.Properties properties ) {
		
		ResourceKey<Block> key = currentKey( Registries.BLOCK );
		if( key != null ) {
			properties.setId( key );
		}
		return properties;
	}
	
	//Item properties with the item id, if the item is created by a RegistryEntry supplier
	@NotNull
	public static Item.Properties itemProperties() {
		
		Item.Properties properties = new Item.Properties();
		ResourceKey<Item> key = currentKey( Registries.ITEM );
		if( key != null ) {
			properties.setId( key );
		}
		return properties;
	}
}
