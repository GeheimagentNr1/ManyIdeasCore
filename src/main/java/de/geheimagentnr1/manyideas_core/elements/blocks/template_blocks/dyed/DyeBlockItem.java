package de.geheimagentnr1.manyideas_core.elements.blocks.template_blocks.dyed;

import de.geheimagentnr1.manyideas_core.util.DyeBlockHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.NotNull;


public class DyeBlockItem extends BlockItem {
	
	
	public DyeBlockItem( @NotNull Block block, @NotNull Item.Properties _properties ) {
		
		super( block, _properties );
		if( FMLEnvironment.dist == Dist.CLIENT ) {
			DyeBlockItemPropertyFunctionRegisterHelper.initDyeBlockItemPropertyGetter( this );
		}
	}
	
	@NotNull
	@Override
	public Component getName( @NotNull ItemStack stack ) {
		
		return Component.translatable(
			getDescriptionId( stack ) + "_" + DyeBlockHelper.getColor( stack ).getSerializedName()
		);
	}
}
