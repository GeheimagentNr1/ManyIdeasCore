package de.geheimagentnr1.manyideas_core.config;

import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import de.geheimagentnr1.minecraft_forge_api.config.AbstractConfig;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.NotNull;


public class ClientConfig extends AbstractConfig {
	
	
	@NotNull
	private static final String DEBUG_KEY = "debug_blocks";
	
	@NotNull
	private static final String ALL_COLORS_IN_ITEM_GROUP_KEY =
		"Should all Colors of Dyed Blocks should be shown in the Creative Tabs?";
	
	public ClientConfig( @NotNull AbstractMod _abstractMod ) {
		
		super( _abstractMod );
	}
	
	@NotNull
	@Override
	public ModConfig.Type type() {
		
		return ModConfig.Type.CLIENT;
	}
	
	@Override
	public boolean isEarlyLoad() {
		
		return true;
	}
	
	@Override
	protected void registerConfigValues() {
		
		registerConfigValue( "Activate Debug Blocks?", DEBUG_KEY, false, true );
		registerConfigValue(
			"Should all Colors of Dyed Blocks should be shown in the Creative Tabs?",
			ALL_COLORS_IN_ITEM_GROUP_KEY,
			true,
			true
		);
	}
	
	public boolean debug() {
		
		return getValue( Boolean.class, DEBUG_KEY );
	}
	
	public boolean allColorsInItemGroup() {
		
		return getValue( Boolean.class, ALL_COLORS_IN_ITEM_GROUP_KEY );
	}
}
