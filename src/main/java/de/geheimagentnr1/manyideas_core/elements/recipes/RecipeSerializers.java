package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
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
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.registries.ObjectHolder;


@SuppressWarnings( { "StaticNonFinalField", "PublicField", "PublicStaticArrayField" } )
public class RecipeSerializers {
	
	
	public static final IRecipeSerializer<?>[] RECIPE_SERIALIZERS = new IRecipeSerializer[] {
		//Dyed
		new DyedRecipeSerializer(),
		//Grinding
		new GrindingRecipeSerializer(),
		//Tablesawing
		new TableSawDiamondRecipeSerializer(),
		new TableSawIronRecipeSerializer(),
		new TableSawStoneRecipeSerializer()
	};
	
	//Dyed
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + DyedRecipe.registry_name )
	public static DyedRecipeSerializer DYED;
	
	//Grinding
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + GrindingRecipe.registry_name )
	public static GrindingRecipeSerializer GRINDING;
	
	//Tablesawing
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawDiamondRecipe.registry_name )
	public static TableSawDiamondRecipeSerializer TABLE_SAWING_DIAMOND;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawIronRecipe.registry_name )
	public static TableSawIronRecipeSerializer TABLE_SAWING_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawStoneRecipe.registry_name )
	public static TableSawStoneRecipeSerializer TABLE_SAWING_STONE;
}
