package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.types.SimpleRecipeType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;


@SuppressWarnings( { "StaticNonFinalField", "PublicField" } )
public class RecipeTypes {
	
	//Dyed
	
	public static RecipeType<DyedRecipe> DYED;
	
	//Grinding
	
	public static RecipeType<GrindingRecipe> GRINDING;
	
	//Tablesawing
	
	public static RecipeType<TableSawDiamondRecipe> TABLE_SAWING_DIAMOND;
	
	public static RecipeType<TableSawIronRecipe> TABLE_SAWING_IRON;
	
	public static RecipeType<TableSawStoneRecipe> TABLE_SAWING_STONE;
	
	private static <T extends Recipe<?>> RecipeType<T> register( String key ) {
		
		ResourceLocation registry_key = new ResourceLocation( ManyIdeasCore.MODID, key );
		return Registry.register( Registry.RECIPE_TYPE, registry_key, new SimpleRecipeType<>( registry_key ) );
	}
	
	public static void init() {
		
		//Dyed
		DYED = register( DyedRecipe.registry_name );
		//Grinding
		GRINDING = register( GrindingRecipe.registry_name );
		//Tablesawing
		TABLE_SAWING_DIAMOND = register( TableSawDiamondRecipe.registry_name );
		TABLE_SAWING_IRON = register( TableSawIronRecipe.registry_name );
		TABLE_SAWING_STONE = register( TableSawStoneRecipe.registry_name );
	}
}
