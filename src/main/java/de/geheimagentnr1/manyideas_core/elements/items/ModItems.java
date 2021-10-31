package de.geheimagentnr1.manyideas_core.elements.items;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.dyes.DyeRainbow;
import de.geheimagentnr1.manyideas_core.elements.items.panels.*;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateGold;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateIron;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateQuartz;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawDiamond;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawIron;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawStone;
import de.geheimagentnr1.manyideas_core.elements.items.tools.MysteriousShears;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.RedstoneKey;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;


@SuppressWarnings( { "StaticNonFinalField", "unused", "PublicField", "PublicStaticArrayField" } )
public class ModItems {
	
	//TODO:
	// F - Funktion fertig
	// I - Item Texture fertig
	// N - Name und Registierungsname vorhanden und fertig
	// R - Rezept fertig
	// T - Tags fertig
	
	public static final Item[] ITEMS = {
		//Dyes
		new DyeRainbow(),//FINRT
		//Panels
		new PanelAcacia(),//FINRT
		new PanelBirch(),//FINRT
		new PanelCrimson(),//FINRT
		new PanelDarkOak(),//FINRT
		new PanelJungle(),//FINRT
		new PanelOak(),//FINRT
		new PanelSpruce(),//FINRT
		new PanelWarped(),//FINRT
		//Plates
		new PlateGold(),//FINRT
		new PlateIron(),//FINRT
		new PlateQuartz(),//FINRT
		//Saws
		new SawDiamond(),//FINRT
		new SawIron(),//FINRT
		new SawStone(),//FINRT
		//Tools
		new MysteriousShears(),//FINRT
		//Tools: Redstone Key
		new RedstoneKey(),//FINZT
	};
	
	//Dyes
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + DyeRainbow.registry_name )
	public static DyeRainbow DYE_RAINBOW;
	
	//Panels
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelAcacia.registry_name )
	public static PanelAcacia PANEL_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelBirch.registry_name )
	public static PanelBirch PANEL_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelCrimson.registry_name )
	public static PanelCrimson PANEL_CRIMSON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelDarkOak.registry_name )
	public static PanelDarkOak PANEL_DARK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelJungle.registry_name )
	public static PanelJungle PANEL_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelOak.registry_name )
	public static PanelOak PANEL_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelSpruce.registry_name )
	public static PanelSpruce PANEL_SPRUCE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PanelWarped.registry_name )
	public static PanelWarped PANEL_WARPED;
	
	//Plates
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlateGold.registry_name )
	public static PlateGold PLATE_GOLD;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlateIron.registry_name )
	public static PlateIron PLATE_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlateQuartz.registry_name )
	public static PlateQuartz PLATE_QUARTZ;
	
	//Saws
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + SawDiamond.registry_name )
	public static SawDiamond SAW_DIAMOND;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + SawIron.registry_name )
	public static SawIron SAW_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + SawStone.registry_name )
	public static SawStone SAW_STONE;
	
	//Tools
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + MysteriousShears.registry_name )
	public static MysteriousShears MYSTERIOUS_SHEARS;
	
	//Tools: Redstone Key
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RedstoneKey.registry_name )
	public static RedstoneKey RESTONE_KEY;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + RedstoneKey.registry_name )
	public static ContainerType<RedstoneKeyContainer> RESTONE_KEY_CONTAINER;
}
