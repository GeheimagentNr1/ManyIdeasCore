package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.PlanksColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.WoodColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockEntity;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockEntityRenderer;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.Mortar;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSawScreen;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamond;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStone;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal.FlowerStraightAllium;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal.FlowerStraightOrchidBlue;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightLilac;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightPeony;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightRoseBush;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightSunflower;
import de.geheimagentnr1.manyideas_core.elements.items.ModItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.RedstoneKey;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryHelper;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryKeys;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "PublicField", "unused" } )
public class ModBlocksRegisterFactory extends BlocksRegisterFactory {
	
	
	@NotNull
	@Override
	protected String getModId() {
		
		return ManyIdeasCore.MODID;
	}
	
	//TODO:
	// B - Block Textur fertig
	// C - Cullface korrekt
	// P - Partikel fertig
	// F - Funktion fertig
	// I - Item fertig
	// N - Name und Registierungsname vorhanden und fertig
	// R - Rezept fertig
	// L - Loottable fertig
	// T - Tags fertig
	
	//Building Blocks: Plankss
	
	public static PlanksColored PLANKS_COLORED;
	
	//Building Blocks: Planks: Seamless
	
	public static PlanksSeamlessAcacia PLANKS_SEAMLESS_ACACIA;
	
	public static PlanksSeamlessBirch PLANKS_SEAMLESS_BIRCH;
	
	public static PlanksSeamlessCrimson PLANKS_SEAMLESS_CRIMSON;
	
	public static PlanksSeamlessDarkOak PLANKS_SEAMLESS_DARK_OAK;
	
	public static PlanksSeamlessJungle PLANKS_SEAMLESS_JUNGLE;
	
	public static PlanksSeamlessMangrove PLANKS_SEAMLESS_MANGROVE;
	
	public static PlanksSeamlessOak PLANKS_SEAMLESS_OAK;
	
	public static PlanksSeamlessSpruce PLANKS_SEAMLESS_SPRUCE;
	
	public static PlanksSeamlessWarped PLANKS_SEAMLESS_WARPED;
	
	//Building Blocks: Blocks: Rainbow
	
	public static RainbowCarpet RAINBOW_CARPET;
	
	public static RainbowConcrete RAINBOW_CONCRETE;
	
	public static RainbowConcretePowder RAINBOW_CONCRETE_POWDER;
	
	public static RainbowStainedGlassBlock RAINBOW_STAINED_GLASS_BLOCK;
	
	public static RainbowStainedGlassPane RAINBOW_STAINED_GLASS_PANE;
	
	public static RainbowTerracotta RAINBOW_TERRACOTTA;
	
	public static RainbowTerracottaGlazed RAINBOW_TERRACOTTA_GLAZED;
	
	public static RainbowWool RAINBOW_WOOL;
	
	//Building Blocks: Woods
	
	public static WoodColored WOOD_COLORED;
	
	//Building Blocks: Woods: Logs Stripped Smooth
	
	public static LogStrippedSmoothAcacia LOG_STRIPPED_SMOOTH_ACACIA;
	
	public static LogStrippedSmoothBirch LOG_STRIPPED_SMOOTH_BIRCH;
	
	public static LogStrippedSmoothCrimson LOG_STRIPPED_SMOOTH_CRIMSON;
	
	public static LogStrippedSmoothDarkOak LOG_STRIPPED_SMOOTH_DARK_OAK;
	
	public static LogStrippedSmoothJungle LOG_STRIPPED_SMOOTH_JUNGLE;
	
	public static LogStrippedSmoothMangrove LOG_STRIPPED_SMOOTH_MANGROVE;
	
	public static LogStrippedSmoothOak LOG_STRIPPED_SMOOTH_OAK;
	
	public static LogStrippedSmoothSpruce LOG_STRIPPED_SMOOTH_SPRUCE;
	
	public static LogStrippedSmoothWarped LOG_STRIPPED_SMOOTH_WARPED;
	
	//Building Blocks: Woods: Woods Stripped Smooth
	
	public static WoodStrippedSmoothAcacia WOOD_STRIPPED_SMOOTH_ACACIA;
	
	public static WoodStrippedSmoothBirch WOOD_STRIPPED_SMOOTH_BIRCH;
	
	public static WoodStrippedSmoothCrimson WOOD_STRIPPED_SMOOTH_CRIMSON;
	
	public static WoodStrippedSmoothDarkOak WOOD_STRIPPED_SMOOTH_DARK_OAK;
	
	public static WoodStrippedSmoothJungle WOOD_STRIPPED_SMOOTH_JUNGLE;
	
	public static WoodStrippedSmoothMangrove WOOD_STRIPPED_SMOOTH_MANGROVE;
	
	public static WoodStrippedSmoothOak WOOD_STRIPPED_SMOOTH_OAK;
	
	public static WoodStrippedSmoothSpruce WOOD_STRIPPED_SMOOTH_SPRUCE;
	
	public static WoodStrippedSmoothWarped WOOD_STRIPPED_SMOOTH_WARPED;
	
	//Dye Crafting Table
	
	public static DyeCraftingTable DYE_CRAFTING_TABLE;
	
	public static MenuType<DyeCraftingTableMenu> DYE_CRAFTING_TABLE_MENU;
	
	//End Block
	
	public static EndBlock END_BLOCK;
	
	public static BlockEntityType<EndBlockEntity> END_BLOCK_ENTITY;
	
	//Mortar
	
	public static Mortar MORTAR;
	
	//Table Saws
	
	public static TableSawDiamond TABLE_SAW_DIAMOND;
	
	public static MenuType<TableSawDiamondMenu> TABLE_SAW_DIAMOND_MENU;
	
	public static TableSawIron TABLE_SAW_IRON;
	
	public static MenuType<TableSawIronMenu> TABLE_SAW_IRON_MENU;
	
	public static TableSawStone TABLE_SAW_STONE;
	
	public static MenuType<TableSawStoneMenu> TABLE_SAW_STONE_MENU;
	
	//Vanilla Blocks: Flowers: Normal
	
	public static FlowerStraightAllium FLOWER_STRAIGHT_ALLIUM;
	
	public static FlowerStraightOrchidBlue FLOWER_STRAIGHT_ORCHID_BLUE;
	
	//Vanilla Blocks: Flowers: Tall
	
	public static FlowerTallStraightLilac FLOWER_TALL_STRAIGHT_LILAC;
	
	public static FlowerTallStraightRoseBush FLOWER_TALL_STRAIGHT_ROSE_BUSH;
	
	public static FlowerTallStraightPeony FLOWER_TALL_STRAIGHT_PEONY;
	
	public static FlowerTallStraightSunflower FLOWER_TALL_STRAIGHT_SUNFLOWER;
	
	private void initializeStaticFields() {
		
		if( DYE_CRAFTING_TABLE == null ) {
			DYE_CRAFTING_TABLE = new DyeCraftingTable();
			DYE_CRAFTING_TABLE_MENU = IMenuTypeExtension.create( ( windowId, inv, data ) -> new DyeCraftingTableMenu( windowId, inv ) );
			END_BLOCK = new EndBlock();
			END_BLOCK_ENTITY = RegistryHelper.buildBlockEntity( EndBlock.registry_name, EndBlockEntity::new, END_BLOCK );
			RAINBOW_WOOL = new RainbowWool();
			TABLE_SAW_DIAMOND = new TableSawDiamond();
			TABLE_SAW_DIAMOND_MENU = IMenuTypeExtension.create( ( windowId, inv, data ) -> new TableSawDiamondMenu( windowId, inv ) );
			TABLE_SAW_IRON = new TableSawIron();
			TABLE_SAW_IRON_MENU = IMenuTypeExtension.create( ( windowId, inv, data ) -> new TableSawIronMenu( windowId, inv ) );
			TABLE_SAW_STONE = new TableSawStone();
			TABLE_SAW_STONE_MENU = IMenuTypeExtension.create( ( windowId, inv, data ) -> new TableSawStoneMenu( windowId, inv ) );
			ModItemsRegisterFactory.RESTONE_KEY_CONTAINER = IMenuTypeExtension.create( ( windowId, inv, data ) -> new RedstoneKeyContainer( windowId, data ) );
		}
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<Block>> blocks() {
		
		initializeStaticFields();
		return List.of(//BCPFINRLT
			//Building Blocks: Planks
			RegistryEntry.create( PlanksColored.registry_name, new PlanksColored() ),
			//BCPFINRLT
			//Building Blocks: Planks: Seamless
			RegistryEntry.create( PlanksSeamlessAcacia.registry_name, new PlanksSeamlessAcacia() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessBirch.registry_name, new PlanksSeamlessBirch() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessCrimson.registry_name, new PlanksSeamlessCrimson() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessDarkOak.registry_name, new PlanksSeamlessDarkOak() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessJungle.registry_name, new PlanksSeamlessJungle() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessMangrove.registry_name, new PlanksSeamlessMangrove() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessOak.registry_name, new PlanksSeamlessOak() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessSpruce.registry_name, new PlanksSeamlessSpruce() ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessWarped.registry_name, new PlanksSeamlessWarped() ),
			//BCPFINRLT
			//Building Blocks: Blocks: Rainbow
			RegistryEntry.create( RainbowCarpet.registry_name, new RainbowCarpet() ),
			//BCPFINRLT
			RegistryEntry.create( RainbowConcrete.registry_name, new RainbowConcrete() ),
			//BCPFINRLT//Kein Rezept
			RegistryEntry.create( RainbowConcretePowder.registry_name, new RainbowConcretePowder() ),
			//BCPFINRLT
			RegistryEntry.create( RainbowStainedGlassBlock.registry_name, new RainbowStainedGlassBlock() ),
			//BCPFINRLT
			RegistryEntry.create( RainbowStainedGlassPane.registry_name, new RainbowStainedGlassPane() ),
			//BCPFINRLT
			RegistryEntry.create( RainbowTerracotta.registry_name, new RainbowTerracotta() ),
			//BCPFINRLT
			RegistryEntry.create( RainbowTerracottaGlazed.registry_name, new RainbowTerracottaGlazed() ),
			//BCPFINRLT
			RegistryEntry.create( RainbowWool.registry_name, RAINBOW_WOOL ),
			//BCPFINRLT//Kein Rezept
			//Building Blocks: Woods
			RegistryEntry.create( WoodColored.registry_name, new WoodColored() ),
			//BCPFINRLT
			//Building Blocks: Woods: Logs Stripped Smooth
			RegistryEntry.create( LogStrippedSmoothAcacia.registry_name, new LogStrippedSmoothAcacia() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothBirch.registry_name, new LogStrippedSmoothBirch() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothCrimson.registry_name, new LogStrippedSmoothCrimson() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothDarkOak.registry_name, new LogStrippedSmoothDarkOak() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothJungle.registry_name, new LogStrippedSmoothJungle() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothMangrove.registry_name, new LogStrippedSmoothMangrove() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothOak.registry_name, new LogStrippedSmoothOak() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothSpruce.registry_name, new LogStrippedSmoothSpruce() ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothWarped.registry_name, new LogStrippedSmoothWarped() ),
			//BCPFINRLT
			//Building Blocks: Woods: Woods Stripped Smooth
			RegistryEntry.create( WoodStrippedSmoothAcacia.registry_name, new WoodStrippedSmoothAcacia() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothBirch.registry_name, new WoodStrippedSmoothBirch() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothCrimson.registry_name, new WoodStrippedSmoothCrimson() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothDarkOak.registry_name, new WoodStrippedSmoothDarkOak() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothJungle.registry_name, new WoodStrippedSmoothJungle() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothMangrove.registry_name, new WoodStrippedSmoothMangrove() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothOak.registry_name, new WoodStrippedSmoothOak() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothSpruce.registry_name, new WoodStrippedSmoothSpruce() ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothWarped.registry_name, new WoodStrippedSmoothWarped() ),
			//BCPFINRLT
			//Dye Crafting Table
			RegistryEntry.create( DyeCraftingTable.registry_name, DYE_CRAFTING_TABLE ),
			//BCPFINRLT
			//End Block
			RegistryEntry.create( EndBlock.registry_name, END_BLOCK ),
			//BCPFINRLT
			//Mortar
			RegistryEntry.create( Mortar.registry_name, new Mortar() ),
			//BCPFINRLT
			//Table Saws
			RegistryEntry.create( TableSawDiamond.registry_name, TABLE_SAW_DIAMOND ),
			//BCPFINRLT
			RegistryEntry.create( TableSawIron.registry_name, TABLE_SAW_IRON ),
			//BCPFINRLT
			RegistryEntry.create( TableSawStone.registry_name, TABLE_SAW_STONE ),
			//BCPFINRLT
			//Vanilla Blocks: Flowers: Normal
			RegistryEntry.create( FlowerStraightAllium.registry_name, new FlowerStraightAllium() ),
			//BCPFINRLT
			RegistryEntry.create( FlowerStraightOrchidBlue.registry_name, new FlowerStraightOrchidBlue() ),
			//BCPFINRLT
			//Vanilla Blocks: Flowers: Tall
			RegistryEntry.create( FlowerTallStraightLilac.registry_name, new FlowerTallStraightLilac() ),
			//BCPFINRLT
			RegistryEntry.create( FlowerTallStraightPeony.registry_name, new FlowerTallStraightPeony() ),
			//BCPFINRLT
			RegistryEntry.create( FlowerTallStraightRoseBush.registry_name, new FlowerTallStraightRoseBush() ),
			//BCPFINRLT
			RegistryEntry.create( FlowerTallStraightSunflower.registry_name, new FlowerTallStraightSunflower() )
			//BCPFINRLT
		);
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<BlockEntityType<?>>> blockEntityTypes() {
		
		return List.of(
			RegistryEntry.create( EndBlock.registry_name, END_BLOCK_ENTITY )
		);
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<MenuType<?>>> menuTypes() {
		
		return List.of(
			RegistryEntry.create( DyeCraftingTable.registry_name, DYE_CRAFTING_TABLE_MENU ),
			RegistryEntry.create( TableSawDiamond.registry_name, TABLE_SAW_DIAMOND_MENU ),
			RegistryEntry.create( TableSawIron.registry_name, TABLE_SAW_IRON_MENU ),
			RegistryEntry.create( TableSawStone.registry_name, TABLE_SAW_STONE_MENU ),
			RegistryEntry.create( RedstoneKey.registry_name, ModItemsRegisterFactory.RESTONE_KEY_CONTAINER )
		);
	}
	
	@SubscribeEvent
	public void handleRegisterEvent( @NotNull net.neoforged.neoforge.registries.RegisterEvent event ) {
		
		doRegisterEvent( event );
	}
	
	@SubscribeEvent
	@Override
	public void handleFMLClientSetupEvent( @NotNull FMLClientSetupEvent event ) {
		
		BlockEntityRenderers.register( END_BLOCK_ENTITY, EndBlockEntityRenderer::new );
	}
	
	@SubscribeEvent
	public void handleRegisterMenuScreensEvent( @NotNull net.neoforged.neoforge.client.event.RegisterMenuScreensEvent event ) {
		
		event.register( DYE_CRAFTING_TABLE_MENU, DyeCraftingTableScreen::new );
		event.register( TABLE_SAW_STONE_MENU, TableSawScreen::new );
		event.register( TABLE_SAW_IRON_MENU, TableSawScreen::new );
		event.register( TABLE_SAW_DIAMOND_MENU, TableSawScreen::new );
	}
}
