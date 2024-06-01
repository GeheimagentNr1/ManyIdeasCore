package de.geheimagentnr1.manyideas_core.special.decoration_renderer.models;

import com.google.gson.JsonObject;
import lombok.Data;


@Data
public class PlayerDecorationItems {

	private JsonObject modded;
	
	private JsonObject vanilla;
}
