package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorStackIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorStackIngredientSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorTagIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorTagIngredientSerializer;
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
	public static final ColorStackIngredientSerializer COLOR_ITEM = new ColorStackIngredientSerializer();
	
	@NotNull
	public static final ColorTagIngredientSerializer COLOR_TAG = new ColorTagIngredientSerializer();
	
	@Override
	protected @NotNull ResourceKey<Registry<IIngredientSerializer<?>>> registryKey() {
		
		return ForgeRegistries.Keys.INGREDIENT_SERIALIZERS;
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<IIngredientSerializer<?>>> elements() {
		
		return List.of(
			RegistryEntry.create( ColorStackIngredient.registry_name, COLOR_ITEM ),
			RegistryEntry.create( ColorTagIngredient.registry_name, COLOR_TAG )
		);
	}
}
