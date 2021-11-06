package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.Planks;
import net.minecraft.item.Item;


public class PlanksSeamlessAcacia extends Planks {
	
	
	public static final String registry_name = "planks_seamless_acacia";
	
	public PlanksSeamlessAcacia() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties _properties ) {
		
		return createBlockItem( ModBlocks.PLANKS_SEAMLESS_ACACIA, _properties, registry_name );
	}
}