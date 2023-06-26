package de.geheimagentnr1.manyideas_core.elements.blocks.vanilla_blocks.metal_smoker;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.util.TranslationKeyHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.SmokerContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.text.ITextComponent;

import org.jetbrains.annotations.NotNull;


public class MetalSmokerTile extends AbstractFurnaceTileEntity {
	
	
	public MetalSmokerTile() {
		
		super( ModBlocks.METAL_SMOKER_TILE, IRecipeType.SMOKING );
	}
	
	@NotNull
	@Override
	protected ITextComponent getDefaultName() {
		
		return TranslationKeyHelper.generateContainerTranslationText( ManyIdeasCore.MODID, MetalSmoker.registry_name );
	}
	
	@Override
	protected int getBurnTime( @NotNull ItemStack stack ) {
		
		return super.getBurnTime( stack ) / 2;
	}
	
	@NotNull
	@Override
	protected Container createMenu( int id, @NotNull PlayerInventory player ) {
		
		return new SmokerContainer( id, player, this, furnaceData );
	}
}
