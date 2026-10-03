package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import com.mojang.serialization.MapCodec;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.numeric.RangeSelectItemModelProperty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


//Item model property "manyideas_core:color" (color ordinal) for the range_dispatch item definitions of dye block items
public class DyeBlockItemPropertyGetter implements RangeSelectItemModelProperty {
	
	
	@NotNull
	public static final ResourceLocation registry_name = ResourceLocation.fromNamespaceAndPath(
		ManyIdeasCore.MODID,
		"color"
	);
	
	@NotNull
	public static final MapCodec<DyeBlockItemPropertyGetter> MAP_CODEC = MapCodec.unit( new DyeBlockItemPropertyGetter() );
	
	@Override
	public float get(
		@NotNull ItemStack stack,
		@Nullable ClientLevel level,
		@Nullable LivingEntity livingEntity,
		int seed ) {
		
		return DyeBlockHelper.getColor( stack ).ordinal();
	}
	
	@NotNull
	@Override
	public MapCodec<DyeBlockItemPropertyGetter> type() {
		
		return MAP_CODEC;
	}
}
