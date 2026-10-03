package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorStackIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorTagIngredient;
import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;


public class ModIngredientSerializersRegisterFactory implements ModEventHandlerInterface {
	
	@SubscribeEvent
	public void handleRegisterEvent( @NotNull RegisterEvent event ) {
		
		event.register( NeoForgeRegistries.Keys.INGREDIENT_TYPES, helper -> {
			helper.register(
				Identifier.fromNamespaceAndPath( ManyIdeasCore.MODID, ColorStackIngredient.registry_name ),
				ColorStackIngredient.TYPE
			);
			helper.register(
				Identifier.fromNamespaceAndPath( ManyIdeasCore.MODID, ColorTagIngredient.registry_name ),
				ColorTagIngredient.TYPE
			);
		} );
	}
}
