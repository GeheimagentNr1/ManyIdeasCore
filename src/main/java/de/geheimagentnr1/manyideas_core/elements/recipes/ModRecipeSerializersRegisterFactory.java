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
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedShapedRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedShapedRecipeSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedShapelessRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedShapelessRecipeSerializer;
import de.geheimagentnr1.manyideas_core.core.registry.ElementsRegisterFactory;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "PublicField", "unused" } )
public class ModRecipeSerializersRegisterFactory extends ElementsRegisterFactory<RecipeSerializer<?>> {
	
	
	@NotNull
	@Override
	protected String getModId() {
		
		return ManyIdeasCore.MODID;
	}
	
	//Dyed
	
	public static RecipeSerializer<DyedShapedRecipe> DYED_SHAPED;
	
	public static RecipeSerializer<DyedShapelessRecipe> DYED_SHAPELESS;
	
	//Grinding
	
	public static RecipeSerializer<GrindingRecipe> GRINDING;
	
	//Tablesawing
	
	public static RecipeSerializer<TableSawDiamondRecipe> TABLE_SAWING_DIAMOND;
	
	public static RecipeSerializer<TableSawIronRecipe> TABLE_SAWING_IRON;
	
	public static RecipeSerializer<TableSawStoneRecipe> TABLE_SAWING_STONE;
	
	@NotNull
	@Override
	protected ResourceKey<Registry<RecipeSerializer<?>>> registryKey() {
		
		return net.minecraft.core.registries.Registries.RECIPE_SERIALIZER;
	}
	
	private void initializeStaticFields() {
		
		if( DYED_SHAPED == null ) {
			DYED_SHAPED = new DyedShapedRecipeSerializer().createSerializer();
			DYED_SHAPELESS = new DyedShapelessRecipeSerializer().createSerializer();
			GRINDING = new GrindingRecipeSerializer().createSerializer();
			TABLE_SAWING_DIAMOND = new TableSawDiamondRecipeSerializer().createSerializer();
			TABLE_SAWING_IRON = new TableSawIronRecipeSerializer().createSerializer();
			TABLE_SAWING_STONE = new TableSawStoneRecipeSerializer().createSerializer();
		}
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<RecipeSerializer<?>>> elements() {
		
		initializeStaticFields();
		return List.of(
			//Dyed
			RegistryEntry.create( DyedShapedRecipe.registry_name, DYED_SHAPED ),
			RegistryEntry.create( DyedShapelessRecipe.registry_name, DYED_SHAPELESS ),
			//Grinding
			RegistryEntry.create( GrindingRecipe.registry_name, GRINDING ),
			//Tablesawing
			RegistryEntry.create( TableSawDiamondRecipe.registry_name, TABLE_SAWING_DIAMOND ),
			RegistryEntry.create( TableSawIronRecipe.registry_name, TABLE_SAWING_IRON ),
			RegistryEntry.create( TableSawStoneRecipe.registry_name, TABLE_SAWING_STONE )
		);
	}
	
	@net.neoforged.bus.api.SubscribeEvent
	public void handleRegisterEvent( @NotNull net.neoforged.neoforge.registries.RegisterEvent event ) {
		
		doRegisterEvent( event );
	}
}
