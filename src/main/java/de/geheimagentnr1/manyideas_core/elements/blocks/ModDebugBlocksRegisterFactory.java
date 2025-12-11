package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.debug.DebugBlockCullface;
import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.core.registry.RegistryEntry;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ModDebugBlocksRegisterFactory extends BlocksRegisterFactory {
	
	
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
	
	@NotNull
	@Override
	protected List<RegistryEntry<Block>> blocks() {
		
		return List.of(//BCPFINRLT
			//Debug
			RegistryEntry.create( DebugBlockCullface.registry_name, new DebugBlockCullface() )//BCFINRLT
		);
	}
	
	@SubscribeEvent
	public void handleRegisterEvent( @NotNull RegisterEvent event ) {
		
		doRegisterEvent( event );
	}
	
	@SubscribeEvent
	@Override
	public void handleFMLClientSetupEvent( @NotNull FMLClientSetupEvent event ) {
	
	}
}
