package de.geheimagentnr1.manyideas_core.elements.commands;

import de.geheimagentnr1.manyideas_core.elements.commands.givedb.ColorArgument;
import de.geheimagentnr1.manyideas_core.elements.commands.givedb.DyeItemArgument;
import de.geheimagentnr1.minecraft_forge_api.registry.ElementsRegisterFactory;
import de.geheimagentnr1.minecraft_forge_api.registry.RegistryEntry;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.commands.synchronization.SingletonArgumentInfo;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ModArgumentTypesRegisterFactory extends ElementsRegisterFactory<ArgumentTypeInfo<?, ?>> {
	
	
	@NotNull
	@Override
	protected ResourceKey<Registry<ArgumentTypeInfo<?, ?>>> registryKey() {
		
		return Registries.COMMAND_ARGUMENT_TYPE;
	}
	
	@NotNull
	@Override
	protected List<RegistryEntry<ArgumentTypeInfo<?, ?>>> elements() {
		
		return List.of(
			RegistryEntry.create(
				ColorArgument.registry_name,
				ArgumentTypeInfos.registerByClass(
					ColorArgument.class,
					SingletonArgumentInfo.contextFree( ColorArgument::color )
				)
			),
			RegistryEntry.create(
				DyeItemArgument.registry_name,
				ArgumentTypeInfos.registerByClass(
					DyeItemArgument.class,
					SingletonArgumentInfo.contextFree( DyeItemArgument::dyeItem )
				)
			)
		);
	}
}
