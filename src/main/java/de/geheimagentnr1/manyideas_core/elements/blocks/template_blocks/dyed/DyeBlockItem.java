package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;


public class DyeBlockItem extends BlockItem {
	
	
	public DyeBlockItem( Block block, Item.Properties _properties, String registry_name ) {
		
		super( block, _properties );
		setRegistryName( registry_name );
		init();
	}
	
	private void init() {
		
		ItemModelsProperties.register( this,
			DyeBlockItemPropertyGetter.registry_name, new DyeBlockItemPropertyGetter()
		);
	}
	
	@Nonnull
	@Override
	public ITextComponent getName( @Nonnull ItemStack stack ) {
		
		return new TranslationTextComponent( getDescriptionId( stack ) + "_" + DyeBlockHelper.getColorName( stack ) );
	}
	
	@Override
	public void fillItemCategory( @Nonnull ItemGroup tab, @Nonnull NonNullList<ItemStack> items ) {
		
		if( allowdedIn( tab ) ) {
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
