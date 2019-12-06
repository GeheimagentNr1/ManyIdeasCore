package de.geheimagentnr1.manyideascore;

import de.geheimagentnr1.manyideascore.setup.ClientProxy;
import de.geheimagentnr1.manyideascore.setup.IProxy;
import de.geheimagentnr1.manyideascore.setup.ModSetup;
import de.geheimagentnr1.manyideascore.setup.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;


@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore {
	
	
	public static final String MODID = "manyideascore";
	
	public static final IProxy proxy = DistExecutor.runForDist( () -> ClientProxy::new, () -> ServerProxy::new );
	
	public static final ModSetup setup = new ModSetup();
}
