package de.geheimagentnr1.manyideas_core.core.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;


public class RegistryEntry<T> {


	@NotNull
	private final String name;

	@Nullable
	private T value;

	@Nullable
	private final Function<ResourceKey<T>, T> factory;

	private RegistryEntry( @NotNull String name, @NotNull T value ) {

		this.name = name;
		this.value = value;
		this.factory = null;
	}

	private RegistryEntry( @NotNull String name, @NotNull Function<ResourceKey<T>, T> factory ) {

		this.name = name;
		this.value = null;
		this.factory = factory;
	}

	@NotNull
	public static <T> RegistryEntry<T> create( @NotNull String name, @NotNull T value ) {

		return new RegistryEntry<>( name, value );
	}

	@NotNull
	public static <T> RegistryEntry<T> create( @NotNull String name, @NotNull Function<ResourceKey<T>, T> factory ) {

		return new RegistryEntry<>( name, factory );
	}
	
	//Creates the value during registration. While the supplier runs, RegistryHelper knows the registry key, so
	//RegistryHelper.withBlockId(..) and RegistryHelper.itemProperties() can set the id required since 1.21.2.
	@NotNull
	public static <T> RegistryEntry<T> create( @NotNull String name, @NotNull Supplier<T> supplier ) {

		return new RegistryEntry<>( name, key -> RegistryHelper.withKey( key, supplier ) );
	}

	public boolean hasFactory() {

		return factory != null;
	}

	@NotNull
	public T build( @NotNull ResourceKey<T> key ) {

		T built = Objects.requireNonNull( factory ).apply( key );
		value = built;
		return built;
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

		return Objects.requireNonNull( value );
	}
}
