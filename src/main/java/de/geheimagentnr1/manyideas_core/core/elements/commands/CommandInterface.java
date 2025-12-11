package de.geheimagentnr1.manyideas_core.core.elements.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;


public interface CommandInterface {
	
	
	@NotNull
	LiteralArgumentBuilder<CommandSourceStack> build();
}
