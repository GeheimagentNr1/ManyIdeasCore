package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.RegistryEntry;
import de.geheimagentnr1.manyideas_core.elements.blocks.debug.DebugBlockCullface;
import net.minecraft.world.level.block.Block;

import java.util.List;


@SuppressWarnings( { "unused", "PublicStaticCollectionField" } )
public class ModBlocksDebug {
	
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
	
	public static final List<RegistryEntry<? extends Block>> BLOCKS = initBlocks();
	
	private static List<RegistryEntry<? extends Block>> initBlocks() {
		
		if( ClientConfig.DEBUG.get() ) {
			return List.of(//BCPFINRLT
				//Debug
				RegistryEntry.create( DebugBlockCullface.registry_name, new DebugBlockCullface() )//BCFINRLT
			);
		} else {
			return List.of();
		}
	}
	
}
