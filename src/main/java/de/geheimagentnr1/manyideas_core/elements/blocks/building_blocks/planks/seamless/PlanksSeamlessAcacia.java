package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.woods.Wood;
import net.minecraft.item.Item;


public class PlanksSeamlessAcacia extends Wood {
	
	
	public static final String registry_name = "planks_seamless_acacia";
	
	public PlanksSeamlessAcacia() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.PLANKS_SEAMLESS_ACACIA, properties, registry_name );
	}
}