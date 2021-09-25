package de.geheimagentnr1.manyideas_core.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;


public class ClientConfig {
	
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	private static final String MOD_NAME = ModLoadingContext.get().getActiveContainer().getModInfo().getDisplayName();
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	
	public static final ForgeConfigSpec CONFIG;
	
	public static final ForgeConfigSpec.BooleanValue DEBUG;
	
	public static final ForgeConfigSpec.BooleanValue ALL_COLORS_IN_ITEM_GROUP;
	
	static {
		
		DEBUG = BUILDER.comment( "Activate Debug Blocks?" )
			.worldRestart()
			.define( "debug_blocks", false );
		ALL_COLORS_IN_ITEM_GROUP =
			BUILDER.comment( "Should all Colors of Dyed Blocks should be shown in the Creative Tabs?" )
				.worldRestart()
				.define( "all_colors_in_item_group", true );
		
		CONFIG = BUILDER.build();
	}
	
	public static void load() {
		
		CommentedFileConfig configData = CommentedFileConfig.builder(
				FMLPaths.CONFIGDIR.get().resolve(
					ManyIdeasCore.MODID + "-" + ModConfig.Type.CLIENT.name().toLowerCase( Locale.ENGLISH ) + ".toml"
				) )
			.sync()
			.autosave()
			.writingMode( WritingMode.REPLACE )
			.build();
		
		LOGGER.info( "Loading \"{}\" Client Config", MOD_NAME );
		configData.load();
		CONFIG.setConfig( configData );
		LOGGER.info( "{} = {}", DEBUG.getPath(), DEBUG.get() );
		LOGGER.info( "{} = {}", ALL_COLORS_IN_ITEM_GROUP.getPath(), ALL_COLORS_IN_ITEM_GROUP.get() );
		LOGGER.info( "\"{}\" Config loaded", MOD_NAME );
	}
}
