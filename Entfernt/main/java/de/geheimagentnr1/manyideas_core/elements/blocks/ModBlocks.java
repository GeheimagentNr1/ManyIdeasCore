package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.rainbow.*;
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
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal.FlowerStraightAllium;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.normal.FlowerStraightOrchidBlue;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightLilac;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightPeony;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightRoseBush;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.flowers_straight.tall.FlowerTallStraightSunflower;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.metal_smoker.MetalSmoker;
import de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.metal_smoker.MetalSmokerTile;
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
		//Cooking Pot
		//new CookingPot(),//BCPFINRLT//TODO: Function
		//Pottery Wheel
		//new PotteryWheel(),//BCPFINRLT//TODO: Function
	};
	
	//Cooking Pot
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + CookingPot.registry_name )
	public static CookingPot COOKING_POT;
	
	//Pottery Wheel
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PotteryWheel.registry_name )
	public static PotteryWheel POTTERY_WHEEL;
}
