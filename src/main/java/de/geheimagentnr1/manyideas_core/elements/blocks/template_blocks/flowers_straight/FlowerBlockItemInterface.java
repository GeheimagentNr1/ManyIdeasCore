package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;


@FunctionalInterface
public interface FlowerBlockItemInterface extends BlockItemInterface {
	
	
	@Nonnull
	@Override
	default Item createBlockItem( Block block, Item.Properties properties, String registry_name ) {
		
		Item item = BlockItemInterface.super.createBlockItem( block, properties, registry_name );
		ComposterBlock.CHANCES.put( item, 0.65F );
		return item;
	}
}
