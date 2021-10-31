package de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.seamless;

import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.blocks.building_blocks.planks.Planks;
import net.minecraft.item.Item;


public class PlanksSeamlessWarped extends Planks {
	
	
	public static final String registry_name = "planks_seamless_warped";
	
	public PlanksSeamlessWarped() {
		
		super( registry_name );
	}
	
	@Override
	public Item getBlockItem( Item.Properties properties ) {
		
		return createBlockItem( ModBlocks.PLANKS_SEAMLESS_WARPED, properties, registry_name );
	}
}