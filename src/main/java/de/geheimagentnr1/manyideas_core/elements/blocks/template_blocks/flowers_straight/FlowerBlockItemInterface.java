package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;


public interface FlowerBlockItemInterface extends BlockItemInterface {
	
	
	@NotNull
	@Override
	default Item getBlockItem( @NotNull Block block, @NotNull Item.Properties properties ) {
		
		//Compostable via data/neoforge/data_maps/item/compostables.json - NeoForge's composter only reads the data map,
		//ComposterBlock.COMPOSTABLES is ignored
		return BlockItemInterface.super.getBlockItem( block, properties );
	}
}
