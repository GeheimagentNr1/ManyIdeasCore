package de.geheimagentnr1.manyideas_core.elements.items;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.planks_and_plates.*;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawDiamond;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawIron;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawStone;
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
	
	public final static Item[] ITEMS = {
		//Planks and Plates
		new PlankAcacia(),//FI_RT
		new PlankBirch(),//FI_RT
		new PlankDarkOak(),//FI_RT
		new PlateIron(),//FI_RT
		new PlankJungle(),//FI_RT
		new PlankOak(),//FI_RT
		new PlankSpruce(),//FI_RT
		//Saws
		new SawDiamond(),//FI_RT
		new SawIron(),//FI_RT
		new SawStone(),//FI_RT
	};
	
	//Plank and Plates
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlankAcacia.registry_name )
	public static PlankAcacia PLANK_ACACIA;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlankBirch.registry_name )
	public static PlankBirch PLANK_BIRCH;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlankDarkOak.registry_name )
	public static PlankDarkOak PLANK_DARK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlateIron.registry_name )
	public static PlateIron PLATE_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlankJungle.registry_name )
	public static PlankJungle PLANK_JUNGLE;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlankOak.registry_name )
	public static PlankOak PLANK_OAK;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + PlankSpruce.registry_name )
	public static PlankSpruce PLANK_SPRUCE;
	
	//Saws
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + SawDiamond.registry_name )
	public static SawDiamond SAW_DIAMOND;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + SawIron.registry_name )
	public static SawIron SAW_IRON;
	
	@ObjectHolder( ManyIdeasCore.MODID + ":" + SawStone.registry_name )
	public static SawStone SAW_STONE;
}
