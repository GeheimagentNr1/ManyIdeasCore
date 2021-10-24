package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes.SingleItemRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;


@SuppressWarnings( { "StaticNonFinalField", "PublicField" } )
public class RecipeSerializers {
	
	
	public static final IRecipeSerializer<?>[] RECIPE_SERIALIZERS = new IRecipeSerializer[] {
		//Dyed
		new DyedRecipeSerializer().setRegistryName( DyedRecipe.registry_name ),
		
		//Grinding
		new SingleItemRecipeSerializer<>( GrindingRecipe::new ).setRegistryName( GrindingRecipe.registry_name ),
		
		//Tablesawing
		new SingleItemRecipeSerializer<>( TableSawDiamondRecipe::new ).setRegistryName( TableSawDiamondRecipe.registry_name ),
		new SingleItemRecipeSerializer<>( TableSawIronRecipe::new ).setRegistryName( TableSawIronRecipe.registry_name ),
		new SingleItemRecipeSerializer<>( TableSawStoneRecipe::new ).setRegistryName( TableSawStoneRecipe.registry_name )
	};
	
	//Dyed
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + DyedRecipe.registry_name )
	public static IRecipeSerializer<DyedRecipe> DYED;
	
	//Grinding
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + GrindingRecipe.registry_name )
	public static IRecipeSerializer<GrindingRecipe> GRINDING;
	
	//Tablesawing
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawDiamondRecipe.registry_name )
	public static IRecipeSerializer<TableSawDiamondRecipe> TABLE_SAWING_DIAMOND;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawIronRecipe.registry_name )
	public static IRecipeSerializer<TableSawIronRecipe> TABLE_SAWING_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawStoneRecipe.registry_name )
	public static IRecipeSerializer<TableSawStoneRecipe> TABLE_SAWING_STONE;
	
	/*@SuppressWarnings( "deprecation" )
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
	}*/
}
