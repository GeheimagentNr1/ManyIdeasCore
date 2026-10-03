package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.manyideas_core.core.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;


public interface FlowerBlockItemInterface extends BlockItemInterface {
	
	
	@NotNull
	@Override
	default Item getBlockItem( @NotNull Block block, @NotNull Item.Properties properties ) {
		
		//ComposterBlock.COMPOSTABLES was removed in 26.3 (NeoForge only used the compostables data map before)
		return BlockItemInterface.super.getBlockItem( block, properties );
	}
}
