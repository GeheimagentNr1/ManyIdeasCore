package de.geheimagentnr1.manyideas_core.core.elements.recipes.types;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public abstract class RecipeTypesRegisterFactory implements ModEventHandlerInterface {
	
	
	@NotNull
	protected abstract String getModId();
	
	@NotNull
	protected abstract List<RegistryEntry<RecipeType<?>>> recipeTypes();
	
	protected void doRegisterEvent( @NotNull RegisterEvent event ) {
		
		String modId = getModId();
		event.register( Registries.RECIPE_TYPE, helper -> {
			for( RegistryEntry<RecipeType<?>> entry : recipeTypes() ) {
				helper.register( entry.getResourceLocation( modId ), entry.getValue() );
			}
		} );
	}
}
