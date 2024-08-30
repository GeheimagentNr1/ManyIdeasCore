package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;


@OnlyIn( Dist.CLIENT )
public class DyeBlockItemPropertyFunctionRegisterHelper {
	
	public static void initDyeBlockItemPropertyGetter( @NotNull DyeBlockItem dyeBlockItem ) {
		
		ItemProperties.register(
			dyeBlockItem,
			DyeBlockItemPropertyGetter.registry_name,
			new DyeBlockItemPropertyGetter()
		);
	}
}
