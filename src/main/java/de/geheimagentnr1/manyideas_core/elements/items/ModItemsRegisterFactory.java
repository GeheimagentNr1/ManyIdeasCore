package de.geheimagentnr1.manyideas_core.elements.items;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
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
import de.geheimagentnr1.manyideas_core.util.CodeNetworkHelper;
import de.geheimagentnr1.manyideas_core.core.elements.items.ItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryKeys;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@SuppressWarnings( { "StaticNonFinalField", "unused", "PublicField" } )
public class ModItemsRegisterFactory extends ItemsRegisterFactory {
	
	
	@NotNull
	@Override
	protected String getModId() {
		
		return ManyIdeasCore.MODID;
	}
	
	//TODO:
	// F - Funktion fertig
	// I - Item Texture fertig
	// N - Name und Registierungsname vorhanden und fertig
	// R - Rezept fertig
	// T - Tags fertig
	
	//Dyes
	
	public static DyeRainbow DYE_RAINBOW;
	
	@NotNull
	public static final DataComponentType<Color> COLOR =
		DataComponentType.<Color> builder()
			.persistent( Color.CODEC )
			.networkSynchronized( StreamCodec.of(
				CodeNetworkHelper::toNetwork,
				CodeNetworkHelper::fromNetwork
			) )
			.build();
	
	//Panels
	
	public static PanelAcacia PANEL_ACACIA;
	
	public static PanelBirch PANEL_BIRCH;
	
	public static PanelCrimson PANEL_CRIMSON;
	
	public static PanelDarkOak PANEL_DARK_OAK;
	
	public static PanelJungle PANEL_JUNGLE;
	
	public static PanelMangrove PANEL_MANGROVE;
	
	public static PanelOak PANEL_OAK;
	
	public static PanelSpruce PANEL_SPRUCE;
	
	public static PanelWarped PANEL_WARPED;
	
	//Plates
	
	public static PlateCopper PLATE_COPPER;
	
	public static PlateGold PLATE_GOLD;
	
	public static PlateIron PLATE_IRON;
	
	public static PlateQuartz PLATE_QUARTZ;
	
	//Saws
	
	public static SawDiamond SAW_DIAMOND;
	
	public static SawIron SAW_IRON;
	
	public static SawStone SAW_STONE;
	
	//Tools
	
	public static MysteriousShears MYSTERIOUS_SHEARS;
	
	//Tools: Redstone Key
	
	public static RedstoneKey RESTONE_KEY;
	
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
			RegistryEntry.create( PanelMangrove.registry_name, new PanelMangrove() ),//FINRT
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
			RegistryEntry.create( RedstoneKey.registry_name, RESTONE_KEY = new RedstoneKey() )//FINZT
		);
	}
	
	@Override
	protected @NotNull List<RegistryEntry<DataComponentType<?>>> dataComponentTypes() {
		
		return List.of(
			RegistryEntry.create(
				"color",
				COLOR
			)
		);
	}
	
	@SubscribeEvent
	public void handleRegisterEvent( @NotNull net.neoforged.neoforge.registries.RegisterEvent event ) {
		
		doRegisterEvent( event );
	}
	
	@SubscribeEvent
	@Override
	public void handleFMLClientSetupEvent( @NotNull FMLClientSetupEvent event ) {
		
	}
	
	@SubscribeEvent
	public void handleRegisterMenuScreensEvent( @NotNull net.neoforged.neoforge.client.event.RegisterMenuScreensEvent event ) {
		
		event.register( RESTONE_KEY_CONTAINER, RedstoneKeyScreen::new );
	}
}
