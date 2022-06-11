package de.geheimagentnr1.manyideas_core.integrations.jei;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneMenu;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

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
		
		registration.addRecipes( DyedRecipeCategory.DYED, JeiDyedRecipe.getRecipes() );
		registration.addRecipes( GrindingRecipeCategory.GRINDING, JeiGrindingRecipe.getRecipes() );
		registration.addRecipes(
			TableSawDiamondRecipeCategory.TABLE_SAWING_DIAMOND,
			JeiTableSawDiamondRecipe.getRecipes()
		);
		registration.addRecipes( TableSawIronRecipeCategory.TABLE_SAWING_IRON, JeiTableSawIronRecipe.getRecipes() );
		registration.addRecipes( TableSawStoneRecipeCategory.TABLE_SAWING_STONE, JeiTableSawStoneRecipe.getRecipes() );
	}
	
	@Override
	public void registerRecipeTransferHandlers( IRecipeTransferRegistration registration ) {
		
		registration.addRecipeTransferHandler(
			DyeCraftingTableMenu.class,
			DyedRecipeCategory.DYED,
			1,
			9,
			10,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawDiamondMenu.class,
			TableSawDiamondRecipeCategory.TABLE_SAWING_DIAMOND,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawDiamondMenu.class,
			TableSawIronRecipeCategory.TABLE_SAWING_IRON,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawDiamondMenu.class,
			TableSawStoneRecipeCategory.TABLE_SAWING_STONE,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawIronMenu.class,
			TableSawIronRecipeCategory.TABLE_SAWING_IRON,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawIronMenu.class,
			TableSawStoneRecipeCategory.TABLE_SAWING_STONE,
			0,
			1,
			2,
			36
		);
		registration.addRecipeTransferHandler(
			TableSawStoneMenu.class,
			TableSawStoneRecipeCategory.TABLE_SAWING_STONE,
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
			DyedRecipeCategory.DYED
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.MORTAR ),
			GrindingRecipeCategory.GRINDING
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_DIAMOND ),
			TableSawDiamondRecipeCategory.TABLE_SAWING_DIAMOND
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_DIAMOND ),
			TableSawIronRecipeCategory.TABLE_SAWING_IRON
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_DIAMOND ),
			TableSawStoneRecipeCategory.TABLE_SAWING_STONE
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_IRON ),
			TableSawIronRecipeCategory.TABLE_SAWING_IRON
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_IRON ),
			TableSawStoneRecipeCategory.TABLE_SAWING_STONE
		);
		registration.addRecipeCatalyst(
			new ItemStack( ModBlocks.TABLE_SAW_STONE ),
			TableSawStoneRecipeCategory.TABLE_SAWING_STONE
		);
	}
}
