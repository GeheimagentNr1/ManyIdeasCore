package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.PlanksColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.WoodColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockTile;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortar.Mortar;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamond;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStone;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal.FlowerStraightAllium;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal.FlowerStraightOrchidBlue;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightLilac;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightPeony;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightRoseBush;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightSunflower;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;


@SuppressWarnings( { "StaticNonFinalField", "PublicField", "PublicStaticArrayField", "unused" } )
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
	
	public static final Block[] BLOCKS = new Block[] {//BCPFINRLT
		//Building Blocks: Planks
		new PlanksColored(),//BCPFINRLT
		//Building Blocks: Planks: Seamless
		new PlanksSeamlessAcacia(),//BCPFINRLT
		new PlanksSeamlessBirch(),//BCPFINRLT
		new PlanksSeamlessCrimson(),//BCPFINRLT
		new PlanksSeamlessDarkOak(),//BCPFINRLT
		new PlanksSeamlessJungle(),//BCPFINRLT
		new PlanksSeamlessOak(),//BCPFINRLT
		new PlanksSeamlessSpruce(),//BCPFINRLT
		new PlanksSeamlessWarped(),//BCPFINRLT
		//Building Blocks: Blocks: Rainbow
		new RainbowCarpet(),//BCPFINRLT
		new RainbowConcrete(),//BCPFINRLT//Kein Rezept
		new RainbowConcretePowder(),//BCPFINRLT
		new RainbowStainedGlassBlock(),//BCPFINRLT
		new RainbowStainedGlassPane(),//BCPFINRLT
		new RainbowTerracotta(),//BCPFINRLT
		new RainbowTerracottaGlazed(),//BCPFINRLT
		new RainbowWool(),//BCPFINRLT//Kein Rezept
		//Building Blocks: Woods
		new WoodColored(),//BCPFINRLT
		//Building Blocks: Woods: Logs Stripped Smooth
		new LogStrippedSmoothAcacia(),//BCPFINRLT
		new LogStrippedSmoothBirch(),//BCPFINRLT
		new LogStrippedSmoothCrimson(),//BCPFINRLT
		new LogStrippedSmoothDarkOak(),//BCPFINRLT
		new LogStrippedSmoothJungle(),//BCPFINRLT
		new LogStrippedSmoothOak(),//BCPFINRLT
		new LogStrippedSmoothSpruce(),//BCPFINRLT
		new LogStrippedSmoothWarped(),//BCPFINRLT
		//Building Blocks: Woods: Woods Stripped Smooth
		new WoodStrippedSmoothAcacia(),//BCPFINRLT
		new WoodStrippedSmoothBirch(),//BCPFINRLT
		new WoodStrippedSmoothCrimson(),//BCPFINRLT
		new WoodStrippedSmoothDarkOak(),//BCPFINRLT
		new WoodStrippedSmoothJungle(),//BCPFINRLT
		new WoodStrippedSmoothOak(),//BCPFINRLT
		new WoodStrippedSmoothSpruce(),//BCPFINRLT
		new WoodStrippedSmoothWarped(),//BCPFINRLT
		//Dye Crafting Table
		new DyeCraftingTable(),//BCPFINRLT
		//End Block
		new EndBlock(),//BCPFINRLT
		//Mortar
		new Mortar(),//BCPFINRLT
		//Table Saws
		new TableSawDiamond(),//BCPFINRLT
		new TableSawIron(),//BCPFINRLT
		new TableSawStone(),//BCPFINRLT
		//Vanilla Blocks: Flowers: Normal
		new FlowerStraightAllium(),//BCPFINRLT
		new FlowerStraightOrchidBlue(),//BCPFINRLT
		//Vanilla Blocks: Flowers: Tall
		new FlowerTallStraightLilac(),//BCPFINRLT
		new FlowerTallStraightPeony(),//BCPFINRLT
		new FlowerTallStraightRoseBush(),//BCPFINRLT
		new FlowerTallStraightSunflower(),//BCPFINRLT
	};
	
	//Building Blocks: Plankss
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksColored.registry_name )
	public static PlanksColored PLANKS_COLORED;
	
	//Building Blocks: Planks: Seamless
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessAcacia.registry_name )
	public static PlanksSeamlessAcacia PLANKS_SEAMLESS_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessBirch.registry_name )
	public static PlanksSeamlessBirch PLANKS_SEAMLESS_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessCrimson.registry_name )
	public static PlanksSeamlessCrimson PLANKS_SEAMLESS_CRIMSON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessDarkOak.registry_name )
	public static PlanksSeamlessDarkOak PLANKS_SEAMLESS_DARK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessJungle.registry_name )
	public static PlanksSeamlessJungle PLANKS_SEAMLESS_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessOak.registry_name )
	public static PlanksSeamlessOak PLANKS_SEAMLESS_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessSpruce.registry_name )
	public static PlanksSeamlessSpruce PLANKS_SEAMLESS_SPRUCE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlanksSeamlessWarped.registry_name )
	public static PlanksSeamlessWarped PLANKS_SEAMLESS_WARPED;
	
	//Building Blocks: Blocks: Rainbow
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowCarpet.registry_name )
	public static RainbowCarpet RAINBOW_CARPET;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowConcrete.registry_name )
	public static RainbowConcrete RAINBOW_CONCRETE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowConcretePowder.registry_name )
	public static RainbowConcretePowder RAINBOW_CONCRETE_POWDER;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowStainedGlassBlock.registry_name )
	public static RainbowStainedGlassBlock RAINBOW_STAINED_GLASS_BLOCK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowStainedGlassPane.registry_name )
	public static RainbowStainedGlassPane RAINBOW_STAINED_GLASS_PANE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowTerracotta.registry_name )
	public static RainbowTerracotta RAINBOW_TERRACOTTA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowTerracottaGlazed.registry_name )
	public static RainbowTerracottaGlazed RAINBOW_TERRACOTTA_GLAZED;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RainbowWool.registry_name )
	public static RainbowWool RAINBOW_WOOL;
	
	//Building Blocks: Woods
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodColored.registry_name )
	public static WoodColored WOOD_COLORED;
	
	//Building Blocks: Woods: Logs Stripped Smooth
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothAcacia.registry_name )
	public static LogStrippedSmoothAcacia LOG_STRIPPED_SMOOTH_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothBirch.registry_name )
	public static LogStrippedSmoothBirch LOG_STRIPPED_SMOOTH_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothCrimson.registry_name )
	public static LogStrippedSmoothCrimson LOG_STRIPPED_SMOOTH_CRIMSON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothDarkOak.registry_name )
	public static LogStrippedSmoothDarkOak LOG_STRIPPED_SMOOTH_DARK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothJungle.registry_name )
	public static LogStrippedSmoothJungle LOG_STRIPPED_SMOOTH_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothOak.registry_name )
	public static LogStrippedSmoothOak LOG_STRIPPED_SMOOTH_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothSpruce.registry_name )
	public static LogStrippedSmoothSpruce LOG_STRIPPED_SMOOTH_SPRUCE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothWarped.registry_name )
	public static LogStrippedSmoothWarped LOG_STRIPPED_SMOOTH_WARPED;
	
	//Building Blocks: Woods: Woods Stripped Smooth
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothAcacia.registry_name )
	public static WoodStrippedSmoothAcacia WOOD_STRIPPED_SMOOTH_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothBirch.registry_name )
	public static WoodStrippedSmoothBirch WOOD_STRIPPED_SMOOTH_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothCrimson.registry_name )
	public static WoodStrippedSmoothCrimson WOOD_STRIPPED_SMOOTH_CRIMSON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothDarkOak.registry_name )
	public static WoodStrippedSmoothDarkOak WOOD_STRIPPED_SMOOTH_DARK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothJungle.registry_name )
	public static WoodStrippedSmoothJungle WOOD_STRIPPED_SMOOTH_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothOak.registry_name )
	public static WoodStrippedSmoothOak WOOD_STRIPPED_SMOOTH_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothSpruce.registry_name )
	public static WoodStrippedSmoothSpruce WOOD_STRIPPED_SMOOTH_SPRUCE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothWarped.registry_name )
	public static WoodStrippedSmoothWarped WOOD_STRIPPED_SMOOTH_WARPED;
	
	//Dye Crafting Table
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + DyeCraftingTable.registry_name )
	public static DyeCraftingTable DYE_CRAFTING_TABLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + DyeCraftingTable.registry_name )
	public static ContainerType<DyeCraftingTableContainer> DYE_CRAFTING_TABLE_CONTAINER;
	
	//End Block
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + EndBlock.registry_name )
	public static EndBlock END_BLOCK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + EndBlock.registry_name )
	public static TileEntityType<EndBlockTile> END_BLOCK_TILE;
	
	//Mortar
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + Mortar.registry_name )
	public static Mortar MORTAR;
	
	//Table Saws
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawDiamond.registry_name )
	public static TableSawDiamond TABLE_SAW_DIAMOND;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawDiamond.registry_name )
	public static ContainerType<TableSawDiamondContainer> TABLE_SAW_DIAMOND_CONTAINER;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawIron.registry_name )
	public static TableSawIron TABLE_SAW_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawIron.registry_name )
	public static ContainerType<TableSawIronContainer> TABLE_SAW_IRON_CONTAINER;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawStone.registry_name )
	public static TableSawStone TABLE_SAW_STONE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + TableSawStone.registry_name )
	public static ContainerType<TableSawStoneContainer> TABLE_SAW_STONE_CONTAINER;
	
	//Vanilla Blocks: Flowers: Normal
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + FlowerStraightAllium.registry_name )
	public static FlowerStraightAllium FLOWER_STRAIGHT_ALLIUM;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + FlowerStraightOrchidBlue.registry_name )
	public static FlowerStraightOrchidBlue FLOWER_STRAIGHT_ORCHID_BLUE;
	
	//Vanilla Blocks: Flowers: Tall
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + FlowerTallStraightLilac.registry_name )
	public static FlowerTallStraightLilac FLOWER_TALL_STRAIGHT_LILAC;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + FlowerTallStraightRoseBush.registry_name )
	public static FlowerTallStraightRoseBush FLOWER_TALL_STRAIGHT_ROSE_BUSH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + FlowerTallStraightPeony.registry_name )
	public static FlowerTallStraightPeony FLOWER_TALL_STRAIGHT_PEONY;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + FlowerTallStraightSunflower.registry_name )
	public static FlowerTallStraightSunflower FLOWER_TALL_STRAIGHT_SUNFLOWER;
}
