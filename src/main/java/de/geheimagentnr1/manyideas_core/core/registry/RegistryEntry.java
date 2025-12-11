package de.geheimagentnr1.manyideas_core.core.registry;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class RegistryEntry<T> {
	
	
	@NotNull
	private final String name;
	
	@NotNull
	private final T value;
	
	private RegistryEntry( @NotNull String name, @NotNull T value ) {
		
		this.name = name;
		this.value = value;
	}
	
	@NotNull
	public static <T> RegistryEntry<T> create( @NotNull String name, @NotNull T value ) {
		
		return new RegistryEntry<>( name, value );
	}
	
	@NotNull
	public String getName() {
		
		return name;
	}
	
	@NotNull
	public ResourceLocation getResourceLocation( @NotNull String namespace ) {
		
		return ResourceLocation.fromNamespaceAndPath( namespace, name );
	}
	
	@NotNull
	public T getValue() {
		
		return value;
	}
}
