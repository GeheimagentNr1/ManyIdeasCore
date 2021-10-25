package de.geheimagentnr1.manyideas_core.handlers;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksDebug;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockTile;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockTileRenderer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamond;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStone;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneContainer;
import de.geheimagentnr1.manyideas_core.elements.commands.ModArgumentTypes;
import de.geheimagentnr1.manyideas_core.elements.item_groups.ModItemGroups;
import de.geheimagentnr1.manyideas_core.elements.items.ModItems;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.RedstoneKey;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyScreen;
import de.geheimagentnr1.manyideas_core.elements.recipes.IngredientSerializer;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.network.Network;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.PlayerDecorationManager;
import de.geheimagentnr1.manyideas_core.util.BlockRegistrationHelper;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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
	public static void handleRegisterRecipeSerialzierEvent( RegistryEvent.Register<IRecipeSerializer<?>> event ) {
		
		for( IngredientSerializer<? extends Ingredient> ingredientSerializer : IngredientSerializers.INGREDIENTS ) {
			CraftingHelper.register( ingredientSerializer.getRegistryNameRL(), ingredientSerializer );
		}
		event.getRegistry().registerAll( RecipeSerializers.RECIPE_SERIALIZERS );
	}
	
	@OnlyIn( Dist.CLIENT )
	@SubscribeEvent
	public static void handleClientSetupEvent( FMLClientSetupEvent event ) {
		
		ScreenManager.register( ModBlocks.DYE_CRAFTING_TABLE_CONTAINER, DyeCraftingTableScreen::new );
		ScreenManager.register( ModBlocks.TABLE_SAW_STONE_CONTAINER, TableSawScreen::new );
		ScreenManager.register( ModBlocks.TABLE_SAW_IRON_CONTAINER, TableSawScreen::new );
		ScreenManager.register( ModBlocks.TABLE_SAW_DIAMOND_CONTAINER, TableSawScreen::new );
		
		ScreenManager.register( ModItems.RESTONE_KEY_CONTAINER, RedstoneKeyScreen::new );
		
		ClientRegistry.bindTileEntityRenderer( ModBlocks.END_BLOCK_TILE, EndBlockTileRenderer::new );
		
		PlayerDecorationManager.initDecorationList();
		
		BlockRegistrationHelper.registerBlockRenderTypes( ModBlocks.BLOCKS );
	}
	
	@SubscribeEvent
	public static void onBlocksRegistry( RegistryEvent.Register<Block> blockRegistryEvent ) {
		
		blockRegistryEvent.getRegistry().registerAll( ModBlocks.BLOCKS );
		blockRegistryEvent.getRegistry().registerAll( ModBlocksDebug.BLOCKS );
	}
	
	@SubscribeEvent
	public static void onItemsRegistry( RegistryEvent.Register<Item> itemRegistryEvent ) {
		
		Item.Properties properties = new Item.Properties().tab( ModItemGroups.MANYIDEAS_CORE_ITEM_GROUP );
		
		BlockRegistrationHelper.registerBlockItems( itemRegistryEvent, ModBlocks.BLOCKS, properties );
		BlockRegistrationHelper.registerBlockItems( itemRegistryEvent, ModBlocksDebug.BLOCKS, properties );
		itemRegistryEvent.getRegistry().registerAll( ModItems.ITEMS );
	}
	
	@SuppressWarnings( "ConstantConditions" )
	@SubscribeEvent
	public static void onTileEntityRegistry( RegistryEvent.Register<TileEntityType<?>> event ) {
		
		event.getRegistry().register( TileEntityType.Builder.of( EndBlockTile::new, ModBlocks.END_BLOCK )
			.build( null )
			.setRegistryName( EndBlock.registry_name ) );
	}
	
	@SubscribeEvent
	public static void onContainerRegistry( RegistryEvent.Register<ContainerType<?>> event ) {
		
		event.getRegistry()
			.register( IForgeContainerType.create( ( windowId, inv, data ) -> new DyeCraftingTableContainer(
				windowId,
				inv
			) ).setRegistryName( DyeCraftingTable.registry_name ) );
		event.getRegistry()
			.register( IForgeContainerType.create( ( windowId, inv, data ) -> new TableSawDiamondContainer(
				windowId,
				inv
			) ).setRegistryName( TableSawDiamond.registry_name ) );
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) -> new TableSawIronContainer(
			windowId,
			inv
		) ).setRegistryName( TableSawIron.registry_name ) );
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) -> new TableSawStoneContainer(
			windowId,
			inv
		) ).setRegistryName( TableSawStone.registry_name ) );
		
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) -> new RedstoneKeyContainer(
			windowId,
			data
		) ).setRegistryName( RedstoneKey.registry_name ) );
	}
	
	@SubscribeEvent
	public static void handleRegisterRecipeSerialzierEvent( RegistryEvent.Register<IRecipeSerializer<?>> event ) {
		
		/*for( IngredientSerializer<? extends Ingredient> ingredientSerializer : IngredientSerializers.INGREDIENTS ) {
			CraftingHelper.register( ingredientSerializer.getRegistryNameRL(), ingredientSerializer );
		}*/
		event.getRegistry().registerAll( RecipeSerializers.RECIPE_SERIALIZERS );
	}
}
