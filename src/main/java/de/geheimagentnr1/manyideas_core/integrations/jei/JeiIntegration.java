package de.geheimagentnr1.manyideas_core.integrations.jei;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneContainer;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed.DyedRecipeCategory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed.JeiDyedRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding.GrindingRecipeCategory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.grinding.JeiGrindingRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_diamond.JeiTableSawDiamondRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_diamond.TableSawDiamondRecipeCategory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron.JeiTableSawIronRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_iron.TableSawIronRecipeCategory;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone.JeiTableSawStoneRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.table_sawing.table_sawing_stone.TableSawStoneRecipeCategory;
import de.geheimagentnr1.manyideas_core.integrations.jei.item_subtypes.DyeBlockItemSubtypeInterpreter;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;


@SuppressWarnings( "unused" )
@JeiPlugin
public class JeiIntegration implements IModPlugin {
	
	
	@Nonnull
	@Override
	public ResourceLocation getPluginUid() {
		
		return new ResourceLocation( ManyIdeasCore.MODID, ManyIdeasCore.MODID );
	}
	
	@Override
	public void registerItemSubtypes( ISubtypeRegistration registration ) {
		
		registration.registerSubtypeInterpreter(
			ModBlocks.PLANKS_COLORED.asItem(),
			new DyeBlockItemSubtypeInterpreter()
		);
		registration.registerSubtypeInterpreter(
			ModBlocks.WOOD_COLORED.asItem(),
			new DyeBlockItemSubtypeInterpreter()
		);
	}
	
	@Override
	public void registerCategories( IRecipeCategoryRegistration registration ) {
		
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registration.addRecipeCategories(
			new DyedRecipeCategory( guiHelper ),
			new GrindingRecipeCategory( guiHelper ),
			new TableSawDiamondRecipeCategory( guiHelper ),
			new TableSawIronRecipeCategory( guiHelper ),
			new TableSawStoneRecipeCategory( guiHelper )
		);
	}
	
	@Override
	public void registerRecipes( IRecipeRegistration registration ) {
		
		registration.addRecipes( JeiDyedRecipe.getRecipes(), DyedRecipeCategory.registry_key );
		registration.addRecipes( JeiGrindingRecipe.getRecipes(), GrindingRecipeCategory.registry_key );
		registration.addRecipes( JeiTableSawDiamondRecipe.getRecipes(), TableSawDiamondRecipeCategory.registry_key );
		registration.addRecipes( JeiTableSawIronRecipe.getRecipes(), TableSawIronRecipeCategory.registry_key );
		registration.addRecipes( JeiTableSawStoneRecipe.getRecipes(), TableSawStoneRecipeCategory.registry_key );
	}
	
	@Override
	public void registerRecipeTransferHandlers( IRecipeTransferRegistration registration ) {
		
		registration.addRecipeTransferHandler(
			DyeCraftingTableContainer.class,
			DyedRecipeCategory.registry_key,
			0,
			9,
			10,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawDiamondContainer.class,
			TableSawDiamondRecipeCategory.registry_key,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawDiamondContainer.class,
			TableSawIronRecipeCategory.registry_key,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawDiamondContainer.class,
			TableSawStoneRecipeCategory.registry_key,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawIronContainer.class,
			TableSawIronRecipeCategory.registry_key,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawIronContainer.class,
			TableSawStoneRecipeCategory.registry_key,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawStoneContainer.class,
			TableSawStoneRecipeCategory.registry_key,
			0,
			1,
			2,
			36
		);
	}
	
	@Override
	public void registerRecipeCatalysts( IRecipeCatalystRegistration registration ) {
		
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.DYE_CRAFTING_TABLE ),
			DyedRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.MORTAR ),
			GrindingRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_DIAMOND ),
			TableSawDiamondRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_DIAMOND ),
			TableSawIronRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_DIAMOND ),
			TableSawStoneRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_IRON ),
			TableSawIronRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_IRON ),
			TableSawStoneRecipeCategory.registry_key
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_STONE ),
			TableSawStoneRecipeCategory.registry_key
		);
	}
}
