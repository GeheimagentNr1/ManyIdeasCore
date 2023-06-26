package de.geheimagentnr1.manyideas_core.util.doors;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.ModBlockStateProperties;
import de.geheimagentnr1.manyideas_core.elements.block_state_properties.OpenedBy;
import de.geheimagentnr1.manyideas_core.elements.items.tools.redstone_key.models.Option;
import de.geheimagentnr1.minecraft_forge_api.util.TranslationKeyHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class OpenedByHelper {
	
	
	@NotNull
	public static final Component OPEN_BY_CONTAINER_TITLE =
		TranslationKeyHelper.generateMessageTranslationTextComponent( ManyIdeasCore.MODID, "opened_by" );
	
	@NotNull
	public static final ResourceLocation ICON_TEXTURES = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/redstone_key/icons/icons_doors.png"
	);
	
	public static boolean canBeOpened( @NotNull BlockState state, boolean onActivated ) {
		
		return switch( state.getValue( ModBlockStateProperties.OPENED_BY ) ) {
			case NOTHING -> false;
			case HAND -> onActivated;
			case REDSTONE -> !onActivated;
			case BOTH -> true;
		};
	}
	
	@NotNull
	public static List<Option> buildOptions() {
		
		OpenedBy[] openedByValues = OpenedBy.values();
		ArrayList<Option> options = new ArrayList<>();
		for( OpenedBy openedBy : openedByValues ) {
			options.add( new Option( openedBy.getTitle(), openedBy.getDescription() ) );
		}
		return options;
	}
	
	public static int getStateIndex( @NotNull BlockState state ) {
		
		return state.getValue( ModBlockStateProperties.OPENED_BY ).ordinal();
	}
}
