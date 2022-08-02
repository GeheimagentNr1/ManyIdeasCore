package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.RegistryEntry;
import de.geheimagentnr1.manyideas_core.elements.RegistryKeys;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.PlanksColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.WoodColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableMenu;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockEntity;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.Mortar;
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
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "PublicField", "unused" } )
public class ModBlocks {
	
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
	
	public static final List<RegistryEntry<? extends Block>> BLOCKS = List.of(//BCPFINRLT
		//Building Blocks: Planks
		RegistryEntry.create( PlanksColored.registry_name, new PlanksColored() ),//BCPFINRLT
		//Building Blocks: Planks: Seamless
		RegistryEntry.create( PlanksSeamlessAcacia.registry_name, new PlanksSeamlessAcacia() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessBirch.registry_name, new PlanksSeamlessBirch() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessCrimson.registry_name, new PlanksSeamlessCrimson() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessDarkOak.registry_name, new PlanksSeamlessDarkOak() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessJungle.registry_name, new PlanksSeamlessJungle() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessOak.registry_name, new PlanksSeamlessOak() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessSpruce.registry_name, new PlanksSeamlessSpruce() ),//BCPFINRLT
		RegistryEntry.create( PlanksSeamlessWarped.registry_name, new PlanksSeamlessWarped() ),//BCPFINRLT
		//Building Blocks: Blocks: Rainbow
		RegistryEntry.create( RainbowCarpet.registry_name, new RainbowCarpet() ),//BCPFINRLT
		RegistryEntry.create( RainbowConcrete.registry_name, new RainbowConcrete() ),//BCPFINRLT//Kein Rezept
		RegistryEntry.create( RainbowConcretePowder.registry_name, new RainbowConcretePowder() ),//BCPFINRLT
		RegistryEntry.create( RainbowStainedGlassBlock.registry_name, new RainbowStainedGlassBlock() ),//BCPFINRLT
		RegistryEntry.create( RainbowStainedGlassPane.registry_name, new RainbowStainedGlassPane() ),//BCPFINRLT
		RegistryEntry.create( RainbowTerracotta.registry_name, new RainbowTerracotta() ),//BCPFINRLT
		RegistryEntry.create( RainbowTerracottaGlazed.registry_name, new RainbowTerracottaGlazed() ),//BCPFINRLT
		RegistryEntry.create( RainbowWool.registry_name, new RainbowWool() ),//BCPFINRLT//Kein Rezept
		//Building Blocks: Woods
		RegistryEntry.create( WoodColored.registry_name, new WoodColored() ),//BCPFINRLT
		//Building Blocks: Woods: Logs Stripped Smooth
		RegistryEntry.create( LogStrippedSmoothAcacia.registry_name, new LogStrippedSmoothAcacia() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothBirch.registry_name, new LogStrippedSmoothBirch() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothCrimson.registry_name, new LogStrippedSmoothCrimson() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothDarkOak.registry_name, new LogStrippedSmoothDarkOak() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothJungle.registry_name, new LogStrippedSmoothJungle() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothOak.registry_name, new LogStrippedSmoothOak() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothSpruce.registry_name, new LogStrippedSmoothSpruce() ),//BCPFINRLT
		RegistryEntry.create( LogStrippedSmoothWarped.registry_name, new LogStrippedSmoothWarped() ),//BCPFINRLT
		//Building Blocks: Woods: Woods Stripped Smooth
		RegistryEntry.create( WoodStrippedSmoothAcacia.registry_name, new WoodStrippedSmoothAcacia() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothBirch.registry_name, new WoodStrippedSmoothBirch() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothCrimson.registry_name, new WoodStrippedSmoothCrimson() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothDarkOak.registry_name, new WoodStrippedSmoothDarkOak() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothJungle.registry_name, new WoodStrippedSmoothJungle() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothOak.registry_name, new WoodStrippedSmoothOak() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothSpruce.registry_name, new WoodStrippedSmoothSpruce() ),//BCPFINRLT
		RegistryEntry.create( WoodStrippedSmoothWarped.registry_name, new WoodStrippedSmoothWarped() ),//BCPFINRLT
		//Dye Crafting Table
		RegistryEntry.create( DyeCraftingTable.registry_name, new DyeCraftingTable() ),//BCPFINRLT
		//End Block
		RegistryEntry.create( EndBlock.registry_name, new EndBlock() ),//BCPFINRLT
		//Mortar
		RegistryEntry.create( Mortar.registry_name, new Mortar() ),//BCPFINRLT
		//Table Saws
		RegistryEntry.create( TableSawDiamond.registry_name, new TableSawDiamond() ),//BCPFINRLT
		RegistryEntry.create( TableSawIron.registry_name, new TableSawIron() ),//BCPFINRLT
		RegistryEntry.create( TableSawStone.registry_name, new TableSawStone() ),//BCPFINRLT
		//Vanilla Blocks: Flowers: Normal
		RegistryEntry.create( FlowerStraightAllium.registry_name, new FlowerStraightAllium() ),//BCPFINRLT
		RegistryEntry.create( FlowerStraightOrchidBlue.registry_name, new FlowerStraightOrchidBlue() ),//BCPFINRLT
		//Vanilla Blocks: Flowers: Tall
		RegistryEntry.create( FlowerTallStraightLilac.registry_name, new FlowerTallStraightLilac() ),//BCPFINRLT
		RegistryEntry.create( FlowerTallStraightPeony.registry_name, new FlowerTallStraightPeony() ),//BCPFINRLT
		RegistryEntry.create( FlowerTallStraightRoseBush.registry_name, new FlowerTallStraightRoseBush() ),//BCPFINRLT
		RegistryEntry.create( FlowerTallStraightSunflower.registry_name, new FlowerTallStraightSunflower() )//BCPFINRLT
	);
	
	//Building Blocks: Plankss
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value =
		ManyIdeasCore.MODID + ":" + PlanksColored.registry_name )
	public static PlanksColored PLANKS_COLORED;
	
	//Building Blocks: Planks: Seamless
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessAcacia.registry_name )
	public static PlanksSeamlessAcacia PLANKS_SEAMLESS_ACACIA;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessBirch.registry_name )
	public static PlanksSeamlessBirch PLANKS_SEAMLESS_BIRCH;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessCrimson.registry_name )
	public static PlanksSeamlessCrimson PLANKS_SEAMLESS_CRIMSON;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessDarkOak.registry_name )
	public static PlanksSeamlessDarkOak PLANKS_SEAMLESS_DARK_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessJungle.registry_name )
	public static PlanksSeamlessJungle PLANKS_SEAMLESS_JUNGLE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessOak.registry_name )
	public static PlanksSeamlessOak PLANKS_SEAMLESS_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessSpruce.registry_name )
	public static PlanksSeamlessSpruce PLANKS_SEAMLESS_SPRUCE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + PlanksSeamlessWarped.registry_name )
	public static PlanksSeamlessWarped PLANKS_SEAMLESS_WARPED;
	
	//Building Blocks: Blocks: Rainbow
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value =
		ManyIdeasCore.MODID + ":" + RainbowCarpet.registry_name )
	public static RainbowCarpet RAINBOW_CARPET;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + RainbowConcrete.registry_name )
	public static RainbowConcrete RAINBOW_CONCRETE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + RainbowConcretePowder.registry_name )
	public static RainbowConcretePowder RAINBOW_CONCRETE_POWDER;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + RainbowStainedGlassBlock.registry_name )
	public static RainbowStainedGlassBlock RAINBOW_STAINED_GLASS_BLOCK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + RainbowStainedGlassPane.registry_name )
	public static RainbowStainedGlassPane RAINBOW_STAINED_GLASS_PANE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + RainbowTerracotta.registry_name )
	public static RainbowTerracotta RAINBOW_TERRACOTTA;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + RainbowTerracottaGlazed.registry_name )
	public static RainbowTerracottaGlazed RAINBOW_TERRACOTTA_GLAZED;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value = ManyIdeasCore.MODID + ":" + RainbowWool.registry_name )
	public static RainbowWool RAINBOW_WOOL;
	
	//Building Blocks: Woods
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value = ManyIdeasCore.MODID + ":" + WoodColored.registry_name )
	public static WoodColored WOOD_COLORED;
	
	//Building Blocks: Woods: Logs Stripped Smooth
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothAcacia.registry_name )
	public static LogStrippedSmoothAcacia LOG_STRIPPED_SMOOTH_ACACIA;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothBirch.registry_name )
	public static LogStrippedSmoothBirch LOG_STRIPPED_SMOOTH_BIRCH;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothCrimson.registry_name )
	public static LogStrippedSmoothCrimson LOG_STRIPPED_SMOOTH_CRIMSON;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothDarkOak.registry_name )
	public static LogStrippedSmoothDarkOak LOG_STRIPPED_SMOOTH_DARK_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothJungle.registry_name )
	public static LogStrippedSmoothJungle LOG_STRIPPED_SMOOTH_JUNGLE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothOak.registry_name )
	public static LogStrippedSmoothOak LOG_STRIPPED_SMOOTH_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothSpruce.registry_name )
	public static LogStrippedSmoothSpruce LOG_STRIPPED_SMOOTH_SPRUCE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + LogStrippedSmoothWarped.registry_name )
	public static LogStrippedSmoothWarped LOG_STRIPPED_SMOOTH_WARPED;
	
	//Building Blocks: Woods: Woods Stripped Smooth
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothAcacia.registry_name )
	public static WoodStrippedSmoothAcacia WOOD_STRIPPED_SMOOTH_ACACIA;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothBirch.registry_name )
	public static WoodStrippedSmoothBirch WOOD_STRIPPED_SMOOTH_BIRCH;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothCrimson.registry_name )
	public static WoodStrippedSmoothCrimson WOOD_STRIPPED_SMOOTH_CRIMSON;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothDarkOak.registry_name )
	public static WoodStrippedSmoothDarkOak WOOD_STRIPPED_SMOOTH_DARK_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothJungle.registry_name )
	public static WoodStrippedSmoothJungle WOOD_STRIPPED_SMOOTH_JUNGLE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothOak.registry_name )
	public static WoodStrippedSmoothOak WOOD_STRIPPED_SMOOTH_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothSpruce.registry_name )
	public static WoodStrippedSmoothSpruce WOOD_STRIPPED_SMOOTH_SPRUCE;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + WoodStrippedSmoothWarped.registry_name )
	public static WoodStrippedSmoothWarped WOOD_STRIPPED_SMOOTH_WARPED;
	
	//Dye Crafting Table
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + DyeCraftingTable.registry_name )
	public static DyeCraftingTable DYE_CRAFTING_TABLE;
	
	@ObjectHolder( registryName = RegistryKeys.MENU_TYPES,
		value = ManyIdeasCore.MODID + ":" + DyeCraftingTable.registry_name )
	public static MenuType<DyeCraftingTableMenu> DYE_CRAFTING_TABLE_MENU;
	
	//End Block
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value = ManyIdeasCore.MODID + ":" + EndBlock.registry_name )
	public static EndBlock END_BLOCK;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCK_ENTITY_TYPES,
		value = ManyIdeasCore.MODID + ":" + EndBlock.registry_name )
	public static BlockEntityType<EndBlockEntity> END_BLOCK_ENTITY;
	
	//Mortar
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value = ManyIdeasCore.MODID + ":" + Mortar.registry_name )
	public static Mortar MORTAR;
	
	//Table Saws
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + TableSawDiamond.registry_name )
	public static TableSawDiamond TABLE_SAW_DIAMOND;
	
	@ObjectHolder( registryName = RegistryKeys.MENU_TYPES,
		value = ManyIdeasCore.MODID + ":" + TableSawDiamond.registry_name )
	public static MenuType<TableSawDiamondMenu> TABLE_SAW_DIAMOND_MENU;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value = ManyIdeasCore.MODID + ":" + TableSawIron.registry_name )
	public static TableSawIron TABLE_SAW_IRON;
	
	@ObjectHolder( registryName = RegistryKeys.MENU_TYPES,
		value = ManyIdeasCore.MODID + ":" + TableSawIron.registry_name )
	public static MenuType<TableSawIronMenu> TABLE_SAW_IRON_MENU;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS, value =
		ManyIdeasCore.MODID + ":" + TableSawStone.registry_name )
	public static TableSawStone TABLE_SAW_STONE;
	
	@ObjectHolder( registryName = RegistryKeys.MENU_TYPES, value =
		ManyIdeasCore.MODID + ":" + TableSawStone.registry_name )
	public static MenuType<TableSawStoneMenu> TABLE_SAW_STONE_MENU;
	
	//Vanilla Blocks: Flowers: Normal
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + FlowerStraightAllium.registry_name )
	public static FlowerStraightAllium FLOWER_STRAIGHT_ALLIUM;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + FlowerStraightOrchidBlue.registry_name )
	public static FlowerStraightOrchidBlue FLOWER_STRAIGHT_ORCHID_BLUE;
	
	//Vanilla Blocks: Flowers: Tall
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + FlowerTallStraightLilac.registry_name )
	public static FlowerTallStraightLilac FLOWER_TALL_STRAIGHT_LILAC;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + FlowerTallStraightRoseBush.registry_name )
	public static FlowerTallStraightRoseBush FLOWER_TALL_STRAIGHT_ROSE_BUSH;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + FlowerTallStraightPeony.registry_name )
	public static FlowerTallStraightPeony FLOWER_TALL_STRAIGHT_PEONY;
	
	@ObjectHolder( registryName = RegistryKeys.BLOCKS,
		value = ManyIdeasCore.MODID + ":" + FlowerTallStraightSunflower.registry_name )
	public static FlowerTallStraightSunflower FLOWER_TALL_STRAIGHT_SUNFLOWER;
}
