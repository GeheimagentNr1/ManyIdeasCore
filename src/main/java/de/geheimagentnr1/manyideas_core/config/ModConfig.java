package de.geheimagentnr1.manyideas_core.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ModConfig {
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final String mod_name = "Many Ideas Core";
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	private static final ForgeConfigSpec CONFIG;
	
	private static final String GENERAL = "general";
	
	public static final ForgeConfigSpec.BooleanValue DEBUG;
	
	public static final ForgeConfigSpec.BooleanValue ALL_COLORS_IN_ITEM_GROUP;
	
	static {
		
		BUILDER.comment( "General settings" ).push( GENERAL );
		DEBUG = BUILDER.comment( "Activated Debug Blocks" ).define( "debug_blocks", false );
		ALL_COLORS_IN_ITEM_GROUP = BUILDER.comment(
			"Should all Colors of Dyed Blocks should be shown in the Creative Tabs" )
			.define( "all_colors_in_item_group", true );
		BUILDER.pop();
		
		CONFIG = BUILDER.build();
	}
	
	public static void load() {
		
		CommentedFileConfig configData = CommentedFileConfig.builder( FMLPaths.CONFIGDIR.get().resolve(
			ManyIdeasCore.MODID + ".toml" ) ).sync().autosave().writingMode( WritingMode.REPLACE ).build();
		
		LOGGER.info( "Loading \"{}\" Config", mod_name );
		configData.load();
		CONFIG.setConfig( configData );
		LOGGER.info( "{} = {}", DEBUG.getPath(), DEBUG.get() );
		LOGGER.info( "{} = {}", ALL_COLORS_IN_ITEM_GROUP.getPath(), ALL_COLORS_IN_ITEM_GROUP.get() );
		LOGGER.info( "\"{}\" Config loaded", mod_name );
	}
}
