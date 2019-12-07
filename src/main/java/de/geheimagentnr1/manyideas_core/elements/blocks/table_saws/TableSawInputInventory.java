package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import net.minecraft.inventory.Inventory;


//package-private
class TableSawInputInventory extends Inventory {
	
	
	private final TableSawContainer tableSawContainer;
	
	//package-private
	TableSawInputInventory( TableSawContainer _tableSawContainer ) {
		
		super( 1 );
		tableSawContainer = _tableSawContainer;
	}
	
	/**
	 * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't
	 * think
	 * it hasn't changed and skip it.
	 */
	@Override
	public void markDirty() {
		
		super.markDirty();
		tableSawContainer.onCraftMatrixChanged( this );
		tableSawContainer.getInventoryUpdateListener().run();
	}
}
