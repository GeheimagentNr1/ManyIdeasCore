package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
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
