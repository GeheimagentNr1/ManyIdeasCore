package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.minecraft_forge_api.elements.recipes.types.RecipeTypesRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.elements.recipes.types.SimpleRecipeType;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "PublicField" } )
public class ModRecipeTypesRegisterFactory extends RecipeTypesRegisterFactory {
	
	//Dyed
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_TYPES,
		value = ManyIdeasCore.MODID + ":" + DyedRecipe.registry_name )
	public static RecipeType<DyedRecipe> DYED;
	
	//Grinding
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_TYPES,
		value = ManyIdeasCore.MODID + ":" + GrindingRecipe.registry_name )
	public static RecipeType<GrindingRecipe> GRINDING;
	
	//Tablesawing
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_TYPES,
		value = ManyIdeasCore.MODID + ":" + TableSawDiamondRecipe.registry_name )
	public static RecipeType<TableSawDiamondRecipe> TABLE_SAWING_DIAMOND;
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_TYPES,
		value = ManyIdeasCore.MODID + ":" + TableSawIronRecipe.registry_name )
	public static RecipeType<TableSawIronRecipe> TABLE_SAWING_IRON;
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_TYPES,
		value = ManyIdeasCore.MODID + ":" + TableSawStoneRecipe.registry_name )
	public static RecipeType<TableSawStoneRecipe> TABLE_SAWING_STONE;
	
	@NotNull
	@Override
	protected ResourceKey<Registry<RecipeType<?>>> registryKey() {
		
		return ForgeRegistries.Keys.RECIPE_TYPES;
	}
	
	@Override
	protected @NotNull List<RegistryEntry<RecipeType<?>>> recipeTypes() {
		
		return List.of(
			RegistryEntry.create(
				DyedRecipe.registry_name,
				new SimpleRecipeType<>( ManyIdeasCore.MODID, DyedRecipe.registry_name )
			),
			RegistryEntry.create(
				GrindingRecipe.registry_name,
				new SimpleRecipeType<>( ManyIdeasCore.MODID, GrindingRecipe.registry_name )
			),
			RegistryEntry.create(
				TableSawDiamondRecipe.registry_name,
				new SimpleRecipeType<>( ManyIdeasCore.MODID, TableSawDiamondRecipe.registry_name )
			),
			RegistryEntry.create(
				TableSawIronRecipe.registry_name,
				new SimpleRecipeType<>( ManyIdeasCore.MODID, TableSawIronRecipe.registry_name )
			),
			RegistryEntry.create(
				TableSawStoneRecipe.registry_name,
				new SimpleRecipeType<>( ManyIdeasCore.MODID, TableSawStoneRecipe.registry_name )
			)
		);
	}
}
