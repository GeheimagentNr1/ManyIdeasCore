package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.PlanksColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.Planks;
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
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSaw;
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
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.flag.FeatureFlags;
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

	@NotNull
	@Override
	protected List<RegistryEntry<Block>> blocks() {

		return List.of(//BCPFINRLT
			//Building Blocks: Planks
			RegistryEntry.create( PlanksColored.registry_name,
				key -> PLANKS_COLORED = new PlanksColored( PlanksColored.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Building Blocks: Planks: Seamless
			RegistryEntry.create( PlanksSeamlessAcacia.registry_name,
				key -> PLANKS_SEAMLESS_ACACIA = new PlanksSeamlessAcacia( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessBirch.registry_name,
				key -> PLANKS_SEAMLESS_BIRCH = new PlanksSeamlessBirch( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessCrimson.registry_name,
				key -> PLANKS_SEAMLESS_CRIMSON = new PlanksSeamlessCrimson( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessDarkOak.registry_name,
				key -> PLANKS_SEAMLESS_DARK_OAK = new PlanksSeamlessDarkOak( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessJungle.registry_name,
				key -> PLANKS_SEAMLESS_JUNGLE = new PlanksSeamlessJungle( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessMangrove.registry_name,
				key -> PLANKS_SEAMLESS_MANGROVE = new PlanksSeamlessMangrove( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessOak.registry_name,
				key -> PLANKS_SEAMLESS_OAK = new PlanksSeamlessOak( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessSpruce.registry_name,
				key -> PLANKS_SEAMLESS_SPRUCE = new PlanksSeamlessSpruce( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( PlanksSeamlessWarped.registry_name,
				key -> PLANKS_SEAMLESS_WARPED = new PlanksSeamlessWarped( Planks.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Building Blocks: Blocks: Rainbow
			RegistryEntry.create( RainbowCarpet.registry_name,
				key -> RAINBOW_CARPET = new RainbowCarpet( RainbowCarpet.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( RainbowConcrete.registry_name,
				key -> RAINBOW_CONCRETE = new RainbowConcrete( RainbowConcrete.createProperties().setId( key ) ) ),
			//BCPFINRLT//Kein Rezept
			RegistryEntry.create( RainbowConcretePowder.registry_name,
				key -> RAINBOW_CONCRETE_POWDER = new RainbowConcretePowder( RainbowConcretePowder.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( RainbowStainedGlassBlock.registry_name,
				key -> RAINBOW_STAINED_GLASS_BLOCK = new RainbowStainedGlassBlock( RainbowStainedGlassBlock.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( RainbowStainedGlassPane.registry_name,
				key -> RAINBOW_STAINED_GLASS_PANE = new RainbowStainedGlassPane( RainbowStainedGlassPane.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( RainbowTerracotta.registry_name,
				key -> RAINBOW_TERRACOTTA = new RainbowTerracotta( RainbowTerracotta.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( RainbowTerracottaGlazed.registry_name,
				key -> RAINBOW_TERRACOTTA_GLAZED = new RainbowTerracottaGlazed( RainbowTerracottaGlazed.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( RainbowWool.registry_name,
				key -> RAINBOW_WOOL = new RainbowWool( RainbowWool.createProperties().setId( key ) ) ),
			//BCPFINRLT//Kein Rezept
			//Building Blocks: Woods
			RegistryEntry.create( WoodColored.registry_name,
				key -> WOOD_COLORED = new WoodColored( WoodColored.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Building Blocks: Woods: Logs Stripped Smooth
			RegistryEntry.create( LogStrippedSmoothAcacia.registry_name,
				key -> LOG_STRIPPED_SMOOTH_ACACIA = new LogStrippedSmoothAcacia( LogStrippedSmoothAcacia.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothBirch.registry_name,
				key -> LOG_STRIPPED_SMOOTH_BIRCH = new LogStrippedSmoothBirch( LogStrippedSmoothBirch.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothCrimson.registry_name,
				key -> LOG_STRIPPED_SMOOTH_CRIMSON = new LogStrippedSmoothCrimson( LogStrippedSmoothCrimson.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothDarkOak.registry_name,
				key -> LOG_STRIPPED_SMOOTH_DARK_OAK = new LogStrippedSmoothDarkOak( LogStrippedSmoothDarkOak.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothJungle.registry_name,
				key -> LOG_STRIPPED_SMOOTH_JUNGLE = new LogStrippedSmoothJungle( LogStrippedSmoothJungle.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothMangrove.registry_name,
				key -> LOG_STRIPPED_SMOOTH_MANGROVE = new LogStrippedSmoothMangrove( LogStrippedSmoothMangrove.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothOak.registry_name,
				key -> LOG_STRIPPED_SMOOTH_OAK = new LogStrippedSmoothOak( LogStrippedSmoothOak.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothSpruce.registry_name,
				key -> LOG_STRIPPED_SMOOTH_SPRUCE = new LogStrippedSmoothSpruce( LogStrippedSmoothSpruce.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( LogStrippedSmoothWarped.registry_name,
				key -> LOG_STRIPPED_SMOOTH_WARPED = new LogStrippedSmoothWarped( LogStrippedSmoothWarped.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Building Blocks: Woods: Woods Stripped Smooth
			RegistryEntry.create( WoodStrippedSmoothAcacia.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_ACACIA = new WoodStrippedSmoothAcacia( WoodStrippedSmoothAcacia.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothBirch.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_BIRCH = new WoodStrippedSmoothBirch( WoodStrippedSmoothBirch.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothCrimson.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_CRIMSON = new WoodStrippedSmoothCrimson( WoodStrippedSmoothCrimson.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothDarkOak.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_DARK_OAK = new WoodStrippedSmoothDarkOak( WoodStrippedSmoothDarkOak.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothJungle.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_JUNGLE = new WoodStrippedSmoothJungle( WoodStrippedSmoothJungle.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothMangrove.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_MANGROVE = new WoodStrippedSmoothMangrove( WoodStrippedSmoothMangrove.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothOak.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_OAK = new WoodStrippedSmoothOak( WoodStrippedSmoothOak.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothSpruce.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_SPRUCE = new WoodStrippedSmoothSpruce( WoodStrippedSmoothSpruce.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( WoodStrippedSmoothWarped.registry_name,
				key -> WOOD_STRIPPED_SMOOTH_WARPED = new WoodStrippedSmoothWarped( WoodStrippedSmoothWarped.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Dye Crafting Table
			RegistryEntry.create( DyeCraftingTable.registry_name,
				key -> DYE_CRAFTING_TABLE = new DyeCraftingTable( DyeCraftingTable.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//End Block
			RegistryEntry.create( EndBlock.registry_name,
				key -> END_BLOCK = new EndBlock( EndBlock.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Mortar
			RegistryEntry.create( Mortar.registry_name,
				key -> MORTAR = new Mortar( Mortar.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Table Saws
			RegistryEntry.create( TableSawDiamond.registry_name,
				key -> TABLE_SAW_DIAMOND = new TableSawDiamond( TableSaw.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( TableSawIron.registry_name,
				key -> TABLE_SAW_IRON = new TableSawIron( TableSaw.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( TableSawStone.registry_name,
				key -> TABLE_SAW_STONE = new TableSawStone( TableSaw.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Vanilla Blocks: Flowers: Normal
			RegistryEntry.create( FlowerStraightAllium.registry_name,
				key -> FLOWER_STRAIGHT_ALLIUM = new FlowerStraightAllium( FlowerStraightAllium.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( FlowerStraightOrchidBlue.registry_name,
				key -> FLOWER_STRAIGHT_ORCHID_BLUE = new FlowerStraightOrchidBlue( FlowerStraightOrchidBlue.createProperties().setId( key ) ) ),
			//BCPFINRLT
			//Vanilla Blocks: Flowers: Tall
			RegistryEntry.create( FlowerTallStraightLilac.registry_name,
				key -> FLOWER_TALL_STRAIGHT_LILAC = new FlowerTallStraightLilac( FlowerTallStraightLilac.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( FlowerTallStraightPeony.registry_name,
				key -> FLOWER_TALL_STRAIGHT_PEONY = new FlowerTallStraightPeony( FlowerTallStraightPeony.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( FlowerTallStraightRoseBush.registry_name,
				key -> FLOWER_TALL_STRAIGHT_ROSE_BUSH = new FlowerTallStraightRoseBush( FlowerTallStraightRoseBush.createProperties().setId( key ) ) ),
			//BCPFINRLT
			RegistryEntry.create( FlowerTallStraightSunflower.registry_name,
				key -> FLOWER_TALL_STRAIGHT_SUNFLOWER = new FlowerTallStraightSunflower( FlowerTallStraightSunflower.createProperties().setId( key ) ) )
			//BCPFINRLT
		);
	}

	@NotNull
	@Override
	protected List<RegistryEntry<BlockEntityType<?>>> blockEntityTypes() {

		END_BLOCK_ENTITY = RegistryHelper.buildBlockEntity( EndBlock.registry_name, EndBlockEntity::new, END_BLOCK );
		return List.of(
			RegistryEntry.create( EndBlock.registry_name, END_BLOCK_ENTITY )
		);
	}

	@NotNull
	@Override
	protected List<RegistryEntry<MenuType<?>>> menuTypes() {

		if( DYE_CRAFTING_TABLE_MENU == null ) {
			DYE_CRAFTING_TABLE_MENU = new MenuType<>( ( windowId, inv ) -> new DyeCraftingTableMenu( windowId, inv ), FeatureFlags.DEFAULT_FLAGS );
			TABLE_SAW_DIAMOND_MENU = new MenuType<>( ( windowId, inv ) -> new TableSawDiamondMenu( windowId, inv ), FeatureFlags.DEFAULT_FLAGS );
			TABLE_SAW_IRON_MENU = new MenuType<>( ( windowId, inv ) -> new TableSawIronMenu( windowId, inv ), FeatureFlags.DEFAULT_FLAGS );
			TABLE_SAW_STONE_MENU = new MenuType<>( ( windowId, inv ) -> new TableSawStoneMenu( windowId, inv ), FeatureFlags.DEFAULT_FLAGS );
			ModItemsRegisterFactory.RESTONE_KEY_CONTAINER = IMenuTypeExtension.create( ( windowId, inv, data ) -> new RedstoneKeyContainer( windowId, data ) );
		}
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
