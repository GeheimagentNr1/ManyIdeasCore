package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.RegistryEntry;
import de.geheimagentnr1.manyideas_core.elements.RegistryKeys;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipeSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "PublicField", "unused" } )
public class RecipeSerializers {
	
	
	public static final List<RegistryEntry<RecipeSerializer<?>>> RECIPE_SERIALIZERS = List.of(
		//Dyed
		RegistryEntry.create( DyedRecipe.registry_name, new DyedRecipeSerializer() ),
		//Grinding
		RegistryEntry.create( GrindingRecipe.registry_name, new GrindingRecipeSerializer() ),
		//Tablesawing
		RegistryEntry.create( TableSawDiamondRecipe.registry_name, new TableSawDiamondRecipeSerializer() ),
		RegistryEntry.create( TableSawIronRecipe.registry_name, new TableSawIronRecipeSerializer() ),
		RegistryEntry.create( TableSawStoneRecipe.registry_name, new TableSawStoneRecipeSerializer() )
	);
	
	//Dyed
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_SERIALIZERS,
		value = ManyIdeasCore.MODID + ":" + DyedRecipe.registry_name )
	public static DyedRecipeSerializer DYED;
	
	//Grinding
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_SERIALIZERS,
		value = ManyIdeasCore.MODID + ":" + GrindingRecipe.registry_name )
	public static GrindingRecipeSerializer GRINDING;
	
	//Tablesawing
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_SERIALIZERS,
		value = ManyIdeasCore.MODID + ":" + TableSawDiamondRecipe.registry_name )
	public static TableSawDiamondRecipeSerializer TABLE_SAWING_DIAMOND;
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_SERIALIZERS,
		value = ManyIdeasCore.MODID + ":" + TableSawIronRecipe.registry_name )
	public static TableSawIronRecipeSerializer TABLE_SAWING_IRON;
	
	@ObjectHolder( registryName = RegistryKeys.RECIPE_SERIALIZERS,
		value = ManyIdeasCore.MODID + ":" + TableSawStoneRecipe.registry_name )
	public static TableSawStoneRecipeSerializer TABLE_SAWING_STONE;
}
