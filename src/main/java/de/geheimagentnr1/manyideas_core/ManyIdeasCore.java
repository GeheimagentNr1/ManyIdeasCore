package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.setup.ClientProxy;
import de.geheimagentnr1.manyideas_core.setup.IProxy;
import de.geheimagentnr1.manyideas_core.setup.ModSetup;
import de.geheimagentnr1.manyideas_core.setup.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;


@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore {
	
	
	public static final String MODID = "manyideas_core";
	
	public static final IProxy proxy = DistExecutor.runForDist( () -> ClientProxy::new, () -> ServerProxy::new );
	
	public static final ModSetup setup = new ModSetup();
}
