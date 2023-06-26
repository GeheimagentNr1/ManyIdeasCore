package de.geheimagentnr1.manyideas_core.elements.items;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.dyes.DyeRainbow;
import de.geheimagentnr1.manyideas_core.elements.items.panels.*;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateCopper;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateGold;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateIron;
import de.geheimagentnr1.manyideas_core.elements.items.plates.PlateQuartz;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawDiamond;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawIron;
import de.geheimagentnr1.manyideas_core.elements.items.saws.SawStone;
import de.geheimagentnr1.manyideas_core.elements.items.tools.MysteriousShears;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.RedstoneKey;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyContainer;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.screen.RedstoneKeyScreen;
import de.geheimagentnr1.minecraft_forge_api.elements.items.ItemsRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryKeys;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ObjectHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "unused", "PublicField" } )
public class ModItemsRegisterFactory extends ItemsRegisterFactory {
	
	//TODO:
	// F - Funktion fertig
	// I - Item Texture fertig
	// N - Name und Registierungsname vorhanden und fertig
	// R - Rezept fertig
	// T - Tags fertig
	
	//Dyes
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + DyeRainbow.registry_name )
	public static DyeRainbow DYE_RAINBOW;
	
	//Panels
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelAcacia.registry_name )
	public static PanelAcacia PANEL_ACACIA;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelBirch.registry_name )
	public static PanelBirch PANEL_BIRCH;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelCrimson.registry_name )
	public static PanelCrimson PANEL_CRIMSON;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelDarkOak.registry_name )
	public static PanelDarkOak PANEL_DARK_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelJungle.registry_name )
	public static PanelJungle PANEL_JUNGLE;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelOak.registry_name )
	public static PanelOak PANEL_OAK;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelSpruce.registry_name )
	public static PanelSpruce PANEL_SPRUCE;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PanelWarped.registry_name )
	public static PanelWarped PANEL_WARPED;
	
	//Plates
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PlateCopper.registry_name )
	public static PlateCopper PLATE_COPPER;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PlateGold.registry_name )
	public static PlateGold PLATE_GOLD;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PlateIron.registry_name )
	public static PlateIron PLATE_IRON;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + PlateQuartz.registry_name )
	public static PlateQuartz PLATE_QUARTZ;
	
	//Saws
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + SawDiamond.registry_name )
	public static SawDiamond SAW_DIAMOND;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + SawIron.registry_name )
	public static SawIron SAW_IRON;
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + SawStone.registry_name )
	public static SawStone SAW_STONE;
	
	//Tools
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS,
		value = ManyIdeasCore.MODID + ":" + MysteriousShears.registry_name )
	public static MysteriousShears MYSTERIOUS_SHEARS;
	
	//Tools: Redstone Key
	
	@ObjectHolder( registryName = RegistryKeys.ITEMS, value = ManyIdeasCore.MODID + ":" + RedstoneKey.registry_name )
	public static RedstoneKey RESTONE_KEY;
	
	@ObjectHolder( registryName = RegistryKeys.MENU_TYPES,
		value = ManyIdeasCore.MODID + ":" + RedstoneKey.registry_name )
	public static MenuType<RedstoneKeyContainer> RESTONE_KEY_CONTAINER;
	
	@NotNull
	@Override
	protected List<RegistryEntry<Item>> items() {
		
		return List.of(
			//Dyes
			RegistryEntry.create( DyeRainbow.registry_name, new DyeRainbow() ),//FINRT
			//Panels
			RegistryEntry.create( PanelAcacia.registry_name, new PanelAcacia() ),//FINRT
			RegistryEntry.create( PanelBirch.registry_name, new PanelBirch() ),//FINRT
			RegistryEntry.create( PanelCrimson.registry_name, new PanelCrimson() ),//FINRT
			RegistryEntry.create( PanelDarkOak.registry_name, new PanelDarkOak() ),//FINRT
			RegistryEntry.create( PanelJungle.registry_name, new PanelJungle() ),//FINRT
			RegistryEntry.create( PanelOak.registry_name, new PanelOak() ),//FINRT
			RegistryEntry.create( PanelSpruce.registry_name, new PanelSpruce() ),//FINRT
			RegistryEntry.create( PanelWarped.registry_name, new PanelWarped() ),//FINRT
			//Plates
			RegistryEntry.create( PlateCopper.registry_name, new PlateCopper() ),//FINRT
			RegistryEntry.create( PlateGold.registry_name, new PlateGold() ),//FINRT
			RegistryEntry.create( PlateIron.registry_name, new PlateIron() ),//FINRT
			RegistryEntry.create( PlateQuartz.registry_name, new PlateQuartz() ),//FINRT
			//Saws
			RegistryEntry.create( SawDiamond.registry_name, new SawDiamond() ),//FINRT
			RegistryEntry.create( SawIron.registry_name, new SawIron() ),//FINRT
			RegistryEntry.create( SawStone.registry_name, new SawStone() ),//FINRT
			//Tools
			RegistryEntry.create( MysteriousShears.registry_name, new MysteriousShears() ),//FINRT
			//Tools: Redstone Key
			RegistryEntry.create( RedstoneKey.registry_name, new RedstoneKey() )//FINZT
		);
	}
	
	@SubscribeEvent
	@Override
	public void handleFMLClientSetupEvent( @NotNull FMLClientSetupEvent event ) {
		
		MenuScreens.register( RESTONE_KEY_CONTAINER, RedstoneKeyScreen::new );
	}
}
