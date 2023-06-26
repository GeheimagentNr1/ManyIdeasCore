package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.flowers_straight;

import de.geheimagentnr1.minecraft_forge_api.elements.blocks.BlockItemInterface;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ComposterBlock;
import org.jetbrains.annotations.NotNull;


public interface FlowerBlockItemInterface extends BlockItemInterface {
	
	
	@NotNull
	@Override
	default Item getBlockItem( @NotNull Block block, @NotNull Item.Properties properties ) {
		
		Item item = BlockItemInterface.super.getBlockItem( block, properties );
		ComposterBlock.COMPOSTABLES.put( item, 0.65F );
		return item;
	}
}
