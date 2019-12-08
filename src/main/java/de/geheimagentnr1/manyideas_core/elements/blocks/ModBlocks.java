package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.WoodColored;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.logs_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.woods_stripped_smooth.*;
import de.geheimagentnr1.manyideas_core.elements.blocks.cooking_pot.CookingPot;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTable;
import de.geheimagentnr1.manyideas_core.elements.blocks.dye_crafting_table.DyeCraftingTableContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlock;
import de.geheimagentnr1.manyideas_core.elements.blocks.end_block.EndBlockTile;
import de.geheimagentnr1.manyideas_core.elements.blocks.mortal.Mortal;
import de.geheimagentnr1.manyideas_core.elements.blocks.pottery_wheel.PotteryWheel;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamond;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond.TableSawDiamondContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIron;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.iron.TableSawIronContainer;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStone;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.stone.TableSawStoneContainer;
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
	
	public final static Block[] BLOCKS = new Block[] {//BCPFINRLT
		//Building Blocks: Woods
		new WoodColored(),//BCPFINRLT
		//Building Blocks: Woods: Logs Stripped Smooth
		new LogStrippedSmoothAcacia(),//BCPFINRLT
		new LogStrippedSmoothBirch(),//BCPFINRLT
		new LogStrippedSmoothDarkOak(),//BCPFINRLT
		new LogStrippedSmoothJungle(),//BCPFINRLT
		new LogStrippedSmoothOak(),//BCPFINRLT
		new LogStrippedSmoothSpruce(),//BCPFINRLT
		//Building Blocks: Woods: Woods Stripped Smooth
		new WoodStrippedSmoothAcacia(),//BCPFINRLT
		new WoodStrippedSmoothBirch(),//BCPFINRLT
		new WoodStrippedSmoothDarkOak(),//BCPFINRLT
		new WoodStrippedSmoothJungle(),//BCPFINRLT
		new WoodStrippedSmoothOak(),//BCPFINRLT
		new WoodStrippedSmoothSpruce(),//BCPFINRLT
		//Cooking Pot
		new CookingPot(),//BCPFINRLT//TODO: Function
		//Dye Crafting Table
		new DyeCraftingTable(),//BCPFINRLT
		//End Block
		new EndBlock(),//BCPFINRLT
		//Mortal
		new Mortal(),//BCPFINRLT
		//Pottery Wheel
		new PotteryWheel(),//BCPFINRLT//TODO: Function
		//Table Saws
		new TableSawDiamond(),//BCPFINRLT
		new TableSawIron(),//BCPFINRLT
		new TableSawStone(),//BCPFINRLT
	};
	
	//Building Blocks: Woods
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodColored.registry_name )
	public static WoodColored WOOD_COLORED;
	
	//Building Blocks: Woods: Logs Stripped Smooth
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothAcacia.registry_name )
	public static LogStrippedSmoothAcacia LOG_STRIPPED_SMOOTH_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothBirch.registry_name )
	public static LogStrippedSmoothBirch LOG_STRIPPED_SMOOTH_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothDarkOak.registry_name )
	public static LogStrippedSmoothDarkOak LOG_STRIPPED_SMOOTH_DARK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothJungle.registry_name )
	public static LogStrippedSmoothJungle LOG_STRIPPED_SMOOTH_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothOak.registry_name )
	public static LogStrippedSmoothOak LOG_STRIPPED_SMOOTH_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + LogStrippedSmoothSpruce.registry_name )
	public static LogStrippedSmoothSpruce LOG_STRIPPED_SMOOTH_SPRUCE;
	
	//Building Blocks: Woods: Woods Stripped Smooth
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothAcacia.registry_name )
	public static WoodStrippedSmoothAcacia WOOD_STRIPPED_SMOOTH_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothBirch.registry_name )
	public static WoodStrippedSmoothBirch WOOD_STRIPPED_SMOOTH_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothDarkOak.registry_name )
	public static WoodStrippedSmoothDarkOak SMOOTH_STRIPPED_DARK_OAK_WOOD;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothJungle.registry_name )
	public static WoodStrippedSmoothJungle WOOD_STRIPPED_SMOOTH_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothOak.registry_name )
	public static WoodStrippedSmoothOak WOOD_STRIPPED_SMOOTH_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + WoodStrippedSmoothSpruce.registry_name )
	public static WoodStrippedSmoothSpruce WOOD_STRIPPED_SMOOTH_SPRUCE;
	
	//Cooking Pot
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + CookingPot.registry_name )
	public static CookingPot COOKING_POT;
	
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
	
	//Mortal
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + Mortal.registry_name )
	public static Mortal MORTAL;
	
	//Pottery Wheel
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PotteryWheel.registry_name )
	public static PotteryWheel POTTERY_WHEEL;
	
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
}
