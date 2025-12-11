package de.geheimagentnr1.manyideas_core.core.registry;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class ElementsRegisterFactory<T> implements ModEventHandlerInterface {
	
	
	@NotNull
	protected abstract String getModId();
	
	@NotNull
	protected abstract ResourceKey<Registry<T>> registryKey();
	
	@NotNull
	protected abstract List<RegistryEntry<T>> elements();
	
	protected void doRegisterEvent( @NotNull RegisterEvent event ) {
		
		String modId = getModId();
		event.register( registryKey(), helper -> {
			for( RegistryEntry<T> entry : elements() ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
	}
}
