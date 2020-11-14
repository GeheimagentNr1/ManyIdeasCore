package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.config.MainConfig;
import de.geheimagentnr1.manyideas_core.setup.ModSetup;
import net.minecraftforge.fml.common.Mod;


@SuppressWarnings( "UtilityClassWithPublicConstructor" )
@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore {
	
	
	public static final String MODID = "manyideas_core";
	
	public static final ModSetup setup = new ModSetup();
	
	public ManyIdeasCore() {
		
		MainConfig.load();
	}
}
