package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.blocks.debug.DebugBlockCullface;
import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlocksRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;


@RequiredArgsConstructor
public class ModDebugBlocksRegisterFactory extends BlocksRegisterFactory {
	
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
	private final ClientConfig clientConfig;
	
	@NotNull
	@Override
	protected List<RegistryEntry<Block>> blocks() {
		
		if( clientConfig.debug() ) {
			return List.of(//BCPFINRLT
				//Debug
				RegistryEntry.create( DebugBlockCullface.registry_name, new DebugBlockCullface() )//BCFINRLT
			);
		} else {
			return List.of();
		}
	}
}
