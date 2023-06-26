package de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;


@RequiredArgsConstructor
@Data
public class Option {
	
	
	@NotNull
	private final String title;
	
	@NotNull
	private final String description;
}
