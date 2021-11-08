package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.elements.block_state_properties.Color;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


@SuppressWarnings( { "WeakerAccess", "deprecation" } )
public class DyeBlockItemPropertyGetter implements ItemPropertyFunction {
	
	
	public static final ResourceLocation registry_name = new ResourceLocation( "color" );
	
	@Override
	public float call(
		@Nonnull ItemStack stack,
		@Nullable ClientLevel level,
		@Nullable LivingEntity livingEntity,
		int seed ) {
		
		Color[] colors = Color.values();
		String color = DyeBlockHelper.getColorName( stack );
		
		for( int i = 0; i < colors.length; i++ ) {
			if( colors[i].getSerializedName().equals( color ) ) {
				return i;
			}
		}
		return -1;
	}
}
