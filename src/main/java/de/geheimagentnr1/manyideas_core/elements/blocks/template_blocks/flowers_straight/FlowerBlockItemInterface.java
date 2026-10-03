package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;


public interface FlowerBlockItemInterface extends BlockItemInterface {
	
	
	@NotNull
	@Override
	default Item getBlockItem( @NotNull Block block, @NotNull Item.Properties properties ) {
		
		//Since 26.3 compostability is an item component (vanilla flowers: COMPOSTABLE_MEDIUM = 65 %)
		return BlockItemInterface.super.getBlockItem(
			block,
			properties.compostable( ContextIntProviders.COMPOSTABLE_MEDIUM )
		);
	}
}
