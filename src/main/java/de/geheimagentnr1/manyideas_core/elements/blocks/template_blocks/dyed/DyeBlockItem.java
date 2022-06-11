package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nonnull;


public class DyeBlockItem extends BlockItem {
	
	
	public DyeBlockItem( Block block, Item.Properties _properties, String registry_name ) {
		
		super( block, _properties );
		//noinspection Convert2MethodRef
		DistExecutor.unsafeRunWhenOn(
			Dist.CLIENT,
			() -> () -> init()
		);
	}
	
	@OnlyIn( Dist.CLIENT )
	private void init() {
		
		ItemProperties.register(
			this,
			DyeBlockItemPropertyGetter.registry_name,
			new DyeBlockItemPropertyGetter()
		);
	}
	
	@Nonnull
	@Override
	public Component getName( @Nonnull ItemStack stack ) {
		
		return Component.translatable( getDescriptionId( stack ) + "_" + DyeBlockHelper.getColorName( stack ) );
	}
	
	@Override
	public void fillItemCategory( @Nonnull CreativeModeTab tab, @Nonnull NonNullList<ItemStack> items ) {
		
		if( allowedIn( tab ) ) {
			if( ClientConfig.ALL_COLORS_IN_ITEM_GROUP.get() ) {
				for( Color color : Color.values() ) {
					items.add( DyeBlockHelper.createItemStackOfItem( this, color ) );
				}
			} else {
				items.add( DyeBlockHelper.createItemStackOfItem( this, Color.WHITE ) );
			}
		}
	}
}
