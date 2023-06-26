package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredient;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredientSerializer;
import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.elements.recipes.ingredients.IngredientSerializersRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ModIngredientSerializersRegisterFactory extends IngredientSerializersRegisterFactory {
	
	//Color
	
	@NotNull
	public static final ColorIngredientSerializer COLOR = new ColorIngredientSerializer();
	
	public ModIngredientSerializersRegisterFactory( @NotNull AbstractMod abstractMod ) {
		
		super( abstractMod );
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<IIngredientSerializer<? extends Ingredient>>> elements() {
		
		return List.of(
			RegistryEntry.create( ColorIngredient.registry_name, COLOR )
		);
	}
}
