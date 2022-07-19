package de.geheimagentnr1.manyideas_core.handlers;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksDebug;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockEntity;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockEntityRenderer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamond;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStone;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneMenu;
import de.geheimagentnr1.manyideas_core.elements.commands.ModArgumentTypes;
import de.geheimagentnr1.manyideas_core.elements.item_groups.ModItemGroups;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.RedstoneKey;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyScreen;
import de.geheimagentnr1.manyideas_core.elements.recipes.IngredientSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.IngredientSerializers;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.network.Network;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.PlayerDecorationManager;
import de.geheimagentnr1.manyideas_core.util.BlockRegistrationHelper;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModEventHandler {
	
	
	@SubscribeEvent
	public static void handleCommonSetupEvent( FMLCommonSetupEvent event ) {
		
		ModArgumentTypes.registerArgumentTypes();
		Network.registerPackets();
	}
	
	@SubscribeEvent
	public static void handleRegisterRecipeTypesEvent( RegisterEvent event ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.RECIPE_TYPES ) ) {
			event.register(
				ForgeRegistries.Keys.RECIPE_TYPES,
				registerHelper -> RecipeTypes.RECIPE_TYPES.forEach( registryEntry -> registerHelper.register(
					registryEntry.getRegistryName(),
					registryEntry.getValue()
				) )
			);
		}
	}
	
	@SubscribeEvent
	public static void handleRegisterRecipeSerialzierEvent( RegisterEvent event ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.RECIPE_SERIALIZERS ) ) {
			for( IngredientSerializer<? extends Ingredient> ingredientSerializer :
				IngredientSerializers.INGREDIENTS ) {
				CraftingHelper.register( ingredientSerializer.getRegistryNameRL(), ingredientSerializer );
			}
			
			event.register(
				ForgeRegistries.Keys.RECIPE_SERIALIZERS,
				registerHelper -> RecipeSerializers.RECIPE_SERIALIZERS.forEach( registryEntry -> registerHelper.register(
					registryEntry.getRegistryName(),
					registryEntry.getValue()
				) )
			);
		}
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	public static void handleClientSetupEvent( FMLClientSetupEvent event ) {
		
		MenuScreens.register( ModBlocks.DYE_CRAFTING_TABLE_MENU, DyeCraftingTableScreen::new );
		MenuScreens.register( ModBlocks.TABLE_SAW_STONE_MENU, TableSawScreen::new );
		MenuScreens.register( ModBlocks.TABLE_SAW_IRON_MENU, TableSawScreen::new );
		MenuScreens.register( ModBlocks.TABLE_SAW_DIAMOND_MENU, TableSawScreen::new );
		
		MenuScreens.register( ModItems.RESTONE_KEY_CONTAINER, RedstoneKeyScreen::new );
		
		BlockEntityRenderers.register( ModBlocks.END_BLOCK_ENTITY, EndBlockEntityRenderer::new );
		
		PlayerDecorationManager.initDecorationList();
		
		BlockRegistrationHelper.registerBlockRenderTypes( ModBlocks.BLOCKS );
	}
	
	@SubscribeEvent
	public static void handleBlockRegistryEvent( RegisterEvent event ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.BLOCKS ) ) {
			event.register(
				ForgeRegistries.Keys.BLOCKS,
				registerHelper -> ModBlocks.BLOCKS.forEach( registryEntry -> registerHelper.register(
					registryEntry.getRegistryName(),
					registryEntry.getValue()
				) )
			);
			event.register(
				ForgeRegistries.Keys.BLOCKS,
				registerHelper -> ModBlocksDebug.BLOCKS.forEach( registryEntry -> registerHelper.register(
					registryEntry.getRegistryName(),
					registryEntry.getValue()
				) )
			);
		}
	}
	
	@SubscribeEvent
	public static void handleItemRegistryEvent( RegisterEvent event ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.ITEMS ) ) {
			Item.Properties properties = new Item.Properties().tab( ModItemGroups.MANYIDEAS_CORE_ITEM_GROUP );
			BlockRegistrationHelper.registerBlockItems( event, ModBlocks.BLOCKS, properties );
			BlockRegistrationHelper.registerBlockItems( event, ModBlocksDebug.BLOCKS, properties );
			event.register(
				ForgeRegistries.Keys.ITEMS,
				registerHelper -> ModItems.ITEMS.forEach( registryEntry -> registerHelper.register(
					registryEntry.getRegistryName(),
					registryEntry.getValue()
				) )
			);
		}
	}
	
	@SuppressWarnings( "ConstantConditions" )
	@SubscribeEvent
	public static void handleBlockEntityRegistryEvent( RegisterEvent event ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.BLOCK_ENTITY_TYPES ) ) {
			event.register(
				ForgeRegistries.Keys.BLOCK_ENTITY_TYPES,
				registerHelper -> registerHelper.register(
					EndBlock.registry_name,
					BlockEntityType.Builder.of( EndBlockEntity::new, ModBlocks.END_BLOCK )
						.build( Util.fetchChoiceType( References.BLOCK_ENTITY, EndBlock.registry_name ) )
				)
			);
		}
	}
	
	@SubscribeEvent
	public static void handleContainerRegistryEvent( RegisterEvent event ) {
		
		if( event.getRegistryKey().equals( ForgeRegistries.Keys.MENU_TYPES ) ) {
			event.register(
				ForgeRegistries.Keys.MENU_TYPES,
				registerHelper -> {
					registerHelper.register(
						DyeCraftingTable.registry_name,
						IForgeMenuType.create( ( menuId, inv, data ) -> new DyeCraftingTableMenu( menuId, inv ) )
					);
					registerHelper.register(
						TableSawDiamond.registry_name,
						IForgeMenuType.create( ( menuId, inv, data ) -> new TableSawDiamondMenu( menuId, inv ) )
					);
					registerHelper.register(
						TableSawIron.registry_name,
						IForgeMenuType.create( ( menuId, inv, data ) -> new TableSawIronMenu( menuId, inv ) )
					);
					registerHelper.register(
						TableSawStone.registry_name,
						IForgeMenuType.create( ( menuId, inv, data ) -> new TableSawStoneMenu( menuId, inv ) )
					);
					registerHelper.register(
						RedstoneKey.registry_name,
						IForgeMenuType.create( ( menuId, inv, data ) -> new RedstoneKeyContainer( menuId, data ) )
					);
				}
			);
		}
	}
}
