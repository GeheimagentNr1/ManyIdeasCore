package de.geheimagentnr1.manyideas_core.elements.block_state_properties;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.interfaces.RedstoneKeyableState;

import javax.annotation.Nonnull;
import java.util.Locale;


public enum OpenedBy implements RedstoneKeyableState {
	NOTHING,
	HAND,
	REDSTONE,
	BOTH;
	
	@Nonnull
	@Override
	public String getName() {
		
		return name().toLowerCase( Locale.ENGLISH );
	}
	
	@Override
	public String getTitle() {
		
		return buildTitle( ManyIdeasCore.MODID );
	}
	
	@Override
	public String getDescription() {
		
		return buildDescription( ManyIdeasCore.MODID );
	}
}
