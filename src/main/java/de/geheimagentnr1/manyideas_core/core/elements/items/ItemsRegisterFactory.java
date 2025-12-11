package de.geheimagentnr1.manyideas_core.core.elements.items;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public abstract class ItemsRegisterFactory implements ModEventHandlerInterface {
	
	
	@NotNull
	private final List<RegistryEntry<Item>> items = new ArrayList<>();
	
	@NotNull
	protected abstract String getModId();
	
	@NotNull
	protected List<RegistryEntry<Item>> items() {
		
		return List.of();
	}
	
	@NotNull
	protected List<RegistryEntry<MenuType<?>>> menuTypes() {
		
		return List.of();
	}
	
	@NotNull
	protected List<RegistryEntry<DataComponentType<?>>> dataComponentTypes() {
		
		return List.of();
	}
	
	@NotNull
	public List<RegistryEntry<Item>> getItems() {
		
		return items;
	}
	
	protected void doRegisterEvent( @NotNull RegisterEvent event ) {
		
		String modId = getModId();
		event.register( Registries.ITEM, helper -> {
			items.clear();
			items.addAll( items() );
			for( RegistryEntry<Item> entry : items ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
		event.register( Registries.MENU, helper -> {
			for( RegistryEntry<MenuType<?>> entry : menuTypes() ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
		event.register( Registries.DATA_COMPONENT_TYPE, helper -> {
			for( RegistryEntry<DataComponentType<?>> entry : dataComponentTypes() ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
	}
}
