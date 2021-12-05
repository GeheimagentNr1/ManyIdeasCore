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
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class ModEventHandler {
	
	
	@SubscribeEvent
	public static void handleCommonSetupEvent( FMLCommonSetupEvent event ) {
		
		ModArgumentTypes.registerArgumentTypes();
		RecipeTypes.init();
		Network.registerPackets();
	}
	
	@SubscribeEvent
	public static void handleRegisterRecipeSerialzierEvent( RegistryEvent.Register<RecipeSerializer<?>> event ) {
		
		for( IngredientSerializer<? extends Ingredient> ingredientSerializer : IngredientSerializers.INGREDIENTS ) {
			CraftingHelper.register( ingredientSerializer.getRegistryNameRL(), ingredientSerializer );
		}
		event.getRegistry().registerAll( RecipeSerializers.RECIPE_SERIALIZERS );
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	public static void handleClientSetupEvent( FMLClientSetupEvent event ) {
		
		MenuScreens.register( ModBlocks.DYE_CRAFTING_TABLE_CONTAINER, DyeCraftingTableScreen::new );
		MenuScreens.register( ModBlocks.TABLE_SAW_STONE_CONTAINER, TableSawScreen::new );
		MenuScreens.register( ModBlocks.TABLE_SAW_IRON_CONTAINER, TableSawScreen::new );
		MenuScreens.register( ModBlocks.TABLE_SAW_DIAMOND_CONTAINER, TableSawScreen::new );
		
		MenuScreens.register( ModItems.RESTONE_KEY_CONTAINER, RedstoneKeyScreen::new );
		
		BlockEntityRenderers.register( ModBlocks.END_BLOCK_ENTITY, EndBlockEntityRenderer::new );
		
		PlayerDecorationManager.initDecorationList();
		
		BlockRegistrationHelper.registerBlockRenderTypes( ModBlocks.BLOCKS );
	}
	
	@SubscribeEvent
	public static void handleBlockRegistryEvent( RegistryEvent.Register<Block> event ) {
		
		event.getRegistry().registerAll( ModBlocks.BLOCKS );
		event.getRegistry().registerAll( ModBlocksDebug.BLOCKS );
	}
	
	@SubscribeEvent
	public static void handleItemRegistryEvent( RegistryEvent.Register<Item> event ) {
		
		Item.Properties properties = new Item.Properties().tab( ModItemGroups.MANYIDEAS_CORE_ITEM_GROUP );
		
		BlockRegistrationHelper.registerBlockItems( event, ModBlocks.BLOCKS, properties );
		BlockRegistrationHelper.registerBlockItems( event, ModBlocksDebug.BLOCKS, properties );
		event.getRegistry().registerAll( ModItems.ITEMS );
	}
	
	@SuppressWarnings( "ConstantConditions" )
	@SubscribeEvent
	public static void handleBlockEntityRegistryEvent( RegistryEvent.Register<BlockEntityType<?>> event ) {
		
		event.getRegistry().register( BlockEntityType.Builder.of( EndBlockEntity::new, ModBlocks.END_BLOCK )
			.build( null )
			.setRegistryName( EndBlock.registry_name ) );
	}
	
	@SubscribeEvent
	public static void handleContainerRegistryEvent( RegistryEvent.Register<MenuType<?>> event ) {
		
		event.getRegistry().register( IForgeMenuType.create( ( menuId, inv, data ) -> new DyeCraftingTableMenu(
			menuId,
			inv
		) ).setRegistryName( DyeCraftingTable.registry_name ) );
		event.getRegistry().register( IForgeMenuType.create( ( menuId, inv, data ) -> new TableSawDiamondMenu(
			menuId,
			inv
		) ).setRegistryName( TableSawDiamond.registry_name ) );
		event.getRegistry().register( IForgeMenuType.create( ( menuId, inv, data ) -> new TableSawIronMenu(
			menuId,
			inv
		) ).setRegistryName( TableSawIron.registry_name ) );
		event.getRegistry().register( IForgeMenuType.create( ( menuId, inv, data ) -> new TableSawStoneMenu(
			menuId,
			inv
		) ).setRegistryName( TableSawStone.registry_name ) );
		
		event.getRegistry().register( IForgeMenuType.create( ( menuId, inv, data ) -> new RedstoneKeyContainer(
			menuId,
			data
		) ).setRegistryName( RedstoneKey.registry_name ) );
	}
}
