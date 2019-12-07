package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.config.ModConfig;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nonnull;


public class DyeBlockItem extends BlockItem {
	
	
	public DyeBlockItem( Block block, Item.Properties _properties ) {
		
		super( block, _properties );
		addProperties();
	}
	
	@Nonnull
	@Override
	public ITextComponent getDisplayName( @Nonnull ItemStack stack ) {
		
		return new TranslationTextComponent( getTranslationKey( stack ) + "_" + DyeBlockHelper.getColorName( stack ) );
	}
	
	private void addProperties() {
		
		for( Color fColor : Color.values() ) {
			addPropertyOverride( new ResourceLocation( fColor.getName() ),
				( itemStack, world, livingEntity ) -> {
					String color = DyeBlockHelper.getColorName( itemStack );
					return color.equals( fColor.getName() ) ? 1 : 0;
				} );
		}
	}
	
	@SuppressWarnings( "ParameterHidesMemberVariable" )
	@Override
	public void fillItemGroup( @Nonnull ItemGroup group, @Nonnull NonNullList<ItemStack> items ) {
		
		if( isInGroup( group ) ) {
			if( ModConfig.ALL_COLORS_IN_ITEM_GROUP.get() ) {
				for( Color color : Color.values() ) {
					items.add( DyeBlockHelper.createItemStackOfItem( this, color ) );
				}
			} else {
				items.add( DyeBlockHelper.createItemStackOfItem( this, Color.WHITE ) );
			}
		}
	}
}
