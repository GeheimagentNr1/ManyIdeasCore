package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredientSerializer;
import de.geheimagentnr1.minecraft_forge_api.registry.ElementsRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ModIngredientSerializersRegisterFactory extends ElementsRegisterFactory<IIngredientSerializer<?>> {
	
	//Color
	
	@NotNull
	public static final ColorIngredientSerializer COLOR = new ColorIngredientSerializer();
	
	@Override
	protected @NotNull ResourceKey<Registry<IIngredientSerializer<?>>> registryKey() {
		
		return ForgeRegistries.Keys.INGREDIENT_SERIALIZERS;
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<IIngredientSerializer<?>>> elements() {
		
		return List.of(
			RegistryEntry.create( ColorIngredient.registry_name, COLOR )
		);
	}
}
