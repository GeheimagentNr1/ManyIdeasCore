package de.geheimagentnr1.manyideascore.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import de.geheimagentnr1.manyideascore.ManyIdeasCore;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ModConfig {
	
	
	private final static Logger LOGGER = LogManager.getLogger();
	
	private final static String mod_name = "Many Ideas Core";
	
	private final static ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	private final static ForgeConfigSpec CONFIG;
	
	private final static String GENERAL = "general";
	
	public final static ForgeConfigSpec.BooleanValue DEBUG;
	
	public final static ForgeConfigSpec.BooleanValue ALL_COLORS_IN_ITEM_GROUP;
	
	static {
		
		BUILDER.comment( "General settings" ).push( GENERAL );
		DEBUG = BUILDER.comment( "Activated Debug Blocks" ).define( "debug_blocks", false );
		ALL_COLORS_IN_ITEM_GROUP = BUILDER.comment(
			"Should all Colors of DyedBlocks should be shown in the Creative Tab" )
			.define( "all_colors_in_item_group", false );
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
