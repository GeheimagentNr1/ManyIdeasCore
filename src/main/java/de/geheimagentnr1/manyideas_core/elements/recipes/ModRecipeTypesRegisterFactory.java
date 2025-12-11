package de.geheimagentnr1.manyideas_core.elements.recipes;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.GrindingRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.core.elements.recipes.types.RecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.core.elements.recipes.types.SimpleRecipeType;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "PublicField" } )
public class ModRecipeTypesRegisterFactory extends RecipeTypesRegisterFactory {
	
	
	@NotNull
	@Override
	protected String getModId() {
		
		return ManyIdeasCore.MODID;
	}
	
	//Dyed
	
	public static RecipeType<DyedRecipe> DYED;
	
	//Grinding
	
	public static RecipeType<GrindingRecipe> GRINDING;
	
	//Tablesawing
	
	public static RecipeType<TableSawDiamondRecipe> TABLE_SAWING_DIAMOND;
	
	public static RecipeType<TableSawIronRecipe> TABLE_SAWING_IRON;
	
	public static RecipeType<TableSawStoneRecipe> TABLE_SAWING_STONE;
	
	@SuppressWarnings( "unchecked" )
	private void initializeStaticFields() {
		
		if( DYED == null ) {
			DYED = new SimpleRecipeType<>( ManyIdeasCore.MODID, DyedRecipe.registry_name );
			GRINDING = new SimpleRecipeType<>( ManyIdeasCore.MODID, GrindingRecipe.registry_name );
			TABLE_SAWING_DIAMOND = new SimpleRecipeType<>( ManyIdeasCore.MODID, TableSawDiamondRecipe.registry_name );
			TABLE_SAWING_IRON = new SimpleRecipeType<>( ManyIdeasCore.MODID, TableSawIronRecipe.registry_name );
			TABLE_SAWING_STONE = new SimpleRecipeType<>( ManyIdeasCore.MODID, TableSawStoneRecipe.registry_name );
		}
	}
	
	@Override
	protected @NotNull List<RegistryEntry<RecipeType<?>>> recipeTypes() {
		
		initializeStaticFields();
		return List.of(
			RegistryEntry.create( DyedRecipe.registry_name, DYED ),
			RegistryEntry.create( GrindingRecipe.registry_name, GRINDING ),
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
