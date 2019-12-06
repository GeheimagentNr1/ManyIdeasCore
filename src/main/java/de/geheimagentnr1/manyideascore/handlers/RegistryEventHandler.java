package de.geheimagentnr1.manyideascore.handlers;

import de.geheimagentnr1.manyideascore.ManyIdeasCore;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideascore.elements.blocks.ModBlocksDebug;
import de.geheimagentnr1.manyideascore.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideascore.elements.blocks.dye_crafting_table.DyeCraftingTableContainer;
import de.geheimagentnr1.manyideascore.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideascore.elements.blocks.end_block.EndBlockTile;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.diamond.TableSawDiamond;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.diamond.TableSawDiamondContainer;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.iron.TableSawIronContainer;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.stone.TableSawStone;
import de.geheimagentnr1.manyideascore.elements.blocks.table_saws.stone.TableSawStoneContainer;
import de.geheimagentnr1.manyideascore.elements.items.ModItems;
import de.geheimagentnr1.manyideascore.util.BlockRegistrationHelper;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@SuppressWarnings( "unused" )
@Mod.EventBusSubscriber( bus = Mod.EventBusSubscriber.Bus.MOD )
public class RegistryEventHandler {
	
	
	@SubscribeEvent
	public static void setup( FMLCommonSetupEvent event ) {
		
		ManyIdeasCore.proxy.init();
	}
	
	@SubscribeEvent
	public static void onBlocksRegistry( RegistryEvent.Register<Block> blockRegistryEvent ) {
		
		blockRegistryEvent.getRegistry().registerAll( ModBlocks.BLOCKS );
		blockRegistryEvent.getRegistry().registerAll( ModBlocksDebug.BLOCKS );
	}
	
	@SubscribeEvent
	public static void onItemsRegistry( RegistryEvent.Register<Item> itemRegistryEvent ) {
		
		Item.Properties properties = new Item.Properties().group( ManyIdeasCore.setup.coreItemGroup );
		
		BlockRegistrationHelper.registerBlockItems( itemRegistryEvent, ModBlocks.BLOCKS, properties );
		BlockRegistrationHelper.registerBlockItems( itemRegistryEvent, ModBlocksDebug.BLOCKS, properties );
		itemRegistryEvent.getRegistry().registerAll( ModItems.ITEMS );
	}
	
	@SuppressWarnings( "ConstantConditions" )
	@SubscribeEvent
	public static void onTileEntityRegistry( RegistryEvent.Register<TileEntityType<?>> event ) {
		
		event.getRegistry().register( TileEntityType.Builder.create( EndBlockTile::new,
			ModBlocks.END_BLOCK ).build( null ).setRegistryName( EndBlock.registry_name ) );
	}
	
	@SubscribeEvent
	public static void onContainerRegistry( RegistryEvent.Register<ContainerType<?>> event ) {
		
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) ->
			new DyeCraftingTableContainer( windowId, inv ) ).setRegistryName( DyeCraftingTable.registry_name ) );
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) ->
			new TableSawDiamondContainer( windowId, inv ) ).setRegistryName( TableSawDiamond.registry_name ) );
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) ->
			new TableSawIronContainer( windowId, inv ) ).setRegistryName( TableSawIron.registry_name ) );
		event.getRegistry().register( IForgeContainerType.create( ( windowId, inv, data ) ->
			new TableSawStoneContainer( windowId, inv ) ).setRegistryName( TableSawStone.registry_name ) );
	}
}
