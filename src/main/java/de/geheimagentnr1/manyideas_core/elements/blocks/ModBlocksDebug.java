package de.geheimagentnr1.manyideas_core.elements.blocks;

import de.geheimagentnr1.manyideas_core.config.ModConfig;
import de.geheimagentnr1.manyideas_core.elements.blocks.debug.DebugBlockCullface;
import net.minecraft.block.Block;


@SuppressWarnings( { "PublicStaticArrayField", "unused" } )
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
	
	public static final Block[] BLOCKS = initBlocks();
	
	private static Block[] initBlocks() {
		
		if( ModConfig.DEBUG.get() ) {
			return new Block[] {//BCPFINRLT
				//Debug
				new DebugBlockCullface(),//BCFINRLT
			};
		} else {
			return new Block[0];
		}
	}
	
}
