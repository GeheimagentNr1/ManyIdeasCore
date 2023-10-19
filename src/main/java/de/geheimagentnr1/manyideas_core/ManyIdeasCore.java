package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModDebugBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.commands.ModArgumentTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.commands.ModCommandsRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.creative_mod_tabs.ModCreativeModeTabRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.items.ModItemsRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModIngredientSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import de.geheimagentnr1.manyideas_core.network.Network;
import de.geheimagentnr1.manyideas_core.special.decoration_renderer.PlayerDecorationManager;
import de.geheimagentnr1.minecraft_forge_api.AbstractMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;


@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore extends AbstractMod {
	
	
	@NotNull
	public static final String MODID = "manyideas_core";
	
	@NotNull
	@Override
	public String getModId() {
		
		return MODID;
	}
	
	@Override
	protected void initMod() {
		
		ClientConfig clientConfig = registerConfig( ClientConfig::new );
		ModBlocksRegisterFactory modBlocksRegisterFactory = registerEventHandler( new ModBlocksRegisterFactory() );
		ModDebugBlocksRegisterFactory modDebugBlocksRegisterFactory = registerEventHandler(
			new ModDebugBlocksRegisterFactory( clientConfig )
		);
		registerEventHandler( new ModArgumentTypesRegisterFactory() );
		registerEventHandler( new ModCommandsRegisterFactory() );
		ModItemsRegisterFactory modItemsRegisterFactory = registerEventHandler( new ModItemsRegisterFactory() );
		registerEventHandler( new ModCreativeModeTabRegisterFactory(
			clientConfig,
			modBlocksRegisterFactory,
			modDebugBlocksRegisterFactory,
			modItemsRegisterFactory
		) );
		registerEventHandler( new ModIngredientSerializersRegisterFactory() );
		registerEventHandler( new ModRecipeSerializersRegisterFactory() );
		registerEventHandler( new ModRecipeTypesRegisterFactory() );
		registerEventHandler( Network.getInstance() );
		DistExecutor.safeRunWhenOn(
			Dist.CLIENT,
			() -> () -> {
				PlayerDecorationManager playerDecorationManager = new PlayerDecorationManager();
				forgeEventBus().addListener( playerDecorationManager::handlePreRenderPlayerEvent );
				modEventBus().addListener( playerDecorationManager::handleFMLClientSetupEvent );
			}
		);
	}
}
