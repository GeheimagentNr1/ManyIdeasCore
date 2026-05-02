package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.core.AbstractMod;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;


@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore extends AbstractMod {
	
	
	@NotNull
	public static final String MODID = "manyideas_core";
	
	public ManyIdeasCore( @NotNull IEventBus modEventBus, @NotNull ModContainer modContainer ) {
		
		super( modEventBus, modContainer );
	}
	
	@NotNull
	@Override
	public String getModId() {
		
		return MODID;
	}
	
	@Override
	protected void initMod() {
		
		ModBlocksRegisterFactory modBlocksRegisterFactory = registerEventHandler( new ModBlocksRegisterFactory() );
		ModDebugBlocksRegisterFactory modDebugBlocksRegisterFactory = registerEventHandler(
			new ModDebugBlocksRegisterFactory()
		);
		registerEventHandler( new ModArgumentTypesRegisterFactory() );
		registerEventHandler( new ModCommandsRegisterFactory() );
		ModItemsRegisterFactory modItemsRegisterFactory = registerEventHandler( new ModItemsRegisterFactory() );
		registerEventHandler( new ModCreativeModeTabRegisterFactory(
			modBlocksRegisterFactory,
			modDebugBlocksRegisterFactory,
			modItemsRegisterFactory
		) );
		registerEventHandler( new ModIngredientSerializersRegisterFactory() );
		registerEventHandler( new ModRecipeSerializersRegisterFactory() );
		registerEventHandler( new ModRecipeTypesRegisterFactory() );
		registerEventHandler( Network.getInstance() );
	}
}
