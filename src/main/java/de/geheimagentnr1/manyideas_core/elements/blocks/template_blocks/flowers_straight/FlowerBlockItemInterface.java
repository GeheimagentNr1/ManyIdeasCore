package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;

import javax.annotation.Nonnull;


@FunctionalInterface
public interface FlowerBlockItemInterface extends BlockItemInterface {
	
	
	@Nonnull
	@Override
	default Item createBlockItem( Block block, Item.Properties properties, String registry_name ) {
		
		Item item = BlockItemInterface.super.createBlockItem( block, properties, registry_name );
		ComposterBlock.COMPOSTABLES.put( item, 0.65F );
		return item;
	}
}
