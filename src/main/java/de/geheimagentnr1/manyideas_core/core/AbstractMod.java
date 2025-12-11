package de.geheimagentnr1.manyideas_core.core;

import de.geheimagentnr1.manyideas_core.core.events.ModEventHandlerInterface;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;


public abstract class AbstractMod {
	
	
	@NotNull
	private final IEventBus modEventBus;
	
	@NotNull
	private final ModContainer modContainer;
	
	protected AbstractMod( @NotNull IEventBus modEventBus, @NotNull ModContainer modContainer ) {
		
		this.modEventBus = modEventBus;
		this.modContainer = modContainer;
		initMod();
	}
	
	@NotNull
	public abstract String getModId();
	
	protected abstract void initMod();
	
	@NotNull
	public IEventBus modEventBus() {
		
		return modEventBus;
	}
	
	@NotNull
	public IEventBus forgeEventBus() {
		
		return NeoForge.EVENT_BUS;
	}
	
	public <T extends ModEventHandlerInterface> T registerEventHandler( @NotNull T eventHandler ) {
		
		modEventBus.register( eventHandler );
		return eventHandler;
	}
}
