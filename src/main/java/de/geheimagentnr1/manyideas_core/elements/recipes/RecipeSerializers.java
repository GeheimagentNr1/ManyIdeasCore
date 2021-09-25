package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;


@SuppressWarnings( { "StaticNonFinalField", "PublicField" } )
public class RecipeSerializers {
	
	//Dyed
	
	public static IRecipeSerializer<DyedRecipe> DYED;
	
	//Grinding
	
	public static IRecipeSerializer<GrindingRecipe> GRINDING;
	
	//Tablesawing
	
	public static IRecipeSerializer<TableSawDiamondRecipe> TABLE_SAWING_DIAMOND;
	
	public static IRecipeSerializer<TableSawIronRecipe> TABLE_SAWING_IRON;
	
	public static IRecipeSerializer<TableSawStoneRecipe> TABLE_SAWING_STONE;
	
	@SuppressWarnings( "deprecation" )
	private static <S extends IRecipeSerializer<T>, T extends IRecipe<?>> S register(
		String key,
		S recipeSerializer ) {
		
		return Registry.register(
			Registry.RECIPE_SERIALIZER,
			new ResourceLocation( ManyIdeasCore.MODID, key ),
			recipeSerializer
		);
	}
	
	public static void init() {
		
		//Dyed
		DYED = register( DyedRecipe.registry_name, new DyedRecipeSerializer() );
		//Grinding
		GRINDING = register( GrindingRecipe.registry_name, new SingleItemRecipeSerializer<>( GrindingRecipe::new ) );
		//Tablesawing
		TABLE_SAWING_DIAMOND = register(
			TableSawDiamondRecipe.registry_name,
			new SingleItemRecipeSerializer<>( TableSawDiamondRecipe::new )
		);
		TABLE_SAWING_IRON = register(
			TableSawIronRecipe.registry_name,
			new SingleItemRecipeSerializer<>( TableSawIronRecipe::new )
		);
		TABLE_SAWING_STONE = register(
			TableSawStoneRecipe.registry_name,
			new SingleItemRecipeSerializer<>( TableSawStoneRecipe::new )
		);
	}
}
