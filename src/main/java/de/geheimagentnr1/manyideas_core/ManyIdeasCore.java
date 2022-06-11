package de.geheimagentnr1.manyideas_core;

import de.geheimagentnr1.manyideas_core.config.ClientConfig;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.ColorArgument;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.DyeItemArgument;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;


@SuppressWarnings( "UtilityClassWithPublicConstructor" )
@Mod( ManyIdeasCore.MODID )
public class ManyIdeasCore {
	
	
	public static final String MODID = "manyideas_core";
	
	private static final DeferredRegister<ArgumentTypeInfo<?, ?>> COMMAND_ARGUMENT_TYPES = DeferredRegister.create(
		Registry.COMMAND_ARGUMENT_TYPE_REGISTRY,
		MODID
	);
	
	private static final RegistryObject<SingletonArgumentInfo<ColorArgument>>
		COLOR_COMMAND_ARGUMENT_TYPE =
		COMMAND_ARGUMENT_TYPES.register(
			ColorArgument.registry_name,
			() -> ArgumentTypeInfos.registerByClass(
				ColorArgument.class,
				SingletonArgumentInfo.contextFree( ColorArgument::color )
			)
		);
	
	private static final RegistryObject<SingletonArgumentInfo<DyeItemArgument>>
		DYE_ITEM_COMMAND_ARGUMENT_TYPE =
		COMMAND_ARGUMENT_TYPES.register(
			DyeItemArgument.registry_name,
			() -> ArgumentTypeInfos.registerByClass(
				DyeItemArgument.class,
				SingletonArgumentInfo.contextFree( DyeItemArgument::dyeItem )
			)
		);
	
	public ManyIdeasCore() {
		
		ClientConfig.load();
		ModLoadingContext.get().registerConfig( ModConfig.Type.CLIENT, ClientConfig.CONFIG );
		COMMAND_ARGUMENT_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
