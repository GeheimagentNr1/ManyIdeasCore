package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


//package-private
class DyeBlockItemPropertyGetter implements IItemPropertyGetter {
	
	
	public static final ResourceLocation registry_name = new ResourceLocation( "color" );
	
	@Override
	public float call( @Nonnull ItemStack stack, @Nullable World world,
		@Nullable LivingEntity livingEntity ) {
		
		Color[] colors = Color.values();
		String color = DyeBlockHelper.getColorName( stack );
		
		for( int i = 0; i < colors.length; i++ ) {
			if( colors[i].getName().equals( color ) ) {
				return i;
			}
		}
		return -1;
	}
}
