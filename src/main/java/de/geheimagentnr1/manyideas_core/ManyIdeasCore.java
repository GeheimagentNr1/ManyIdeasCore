package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@SuppressWarnings( "UtilityClassWithPublicConstructor" )
@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore {
	
	
	public static final String MODID = "manyideas_core";
	
	public ManyIdeasCore() {
		
		ClientConfig.load();
		ModLoadingContext.get().registerConfig( ModConfig.Type.CLIENT, ClientConfig.CONFIG );
	}
}
