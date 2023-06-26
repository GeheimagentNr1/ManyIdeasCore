package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import net.minecraft.world.SimpleContainer;
import org.jetbrains.annotations.NotNull;


//package-private
class TableSawInputContainer extends SimpleContainer {
	
	
	@NotNull
	private final TableSawMenu tableSawMenu;
	
	//package-private
	TableSawInputContainer( @NotNull TableSawMenu _tableSawMenu ) {
		
		super( 1 );
		tableSawMenu = _tableSawMenu;
	}
	
	/**
	 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't
	 * think
	 * it hasn't changed and skip it.
	 */
	@Override
	public void setChanged() {
		
		super.setChanged();
		tableSawMenu.slotsChanged( this );
		tableSawMenu.getInventoryUpdateListener().run();
	}
}
