package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.diamond;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.table_saws.TableSaw;
import de.geheimagentnr1.minecraft_forge_api.util.TranslationKeyHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.jetbrains.annotations.NotNull;


public class TableSawDiamond extends TableSaw {
	
	
	@NotNull
	public static final String registry_name = "table_saw_diamond";
	
	@NotNull
	private static final Component CONTAINER_TITLE = TranslationKeyHelper.generateContainerTranslationText(
		ManyIdeasCore.MODID,
		registry_name
	);
	
	@NotNull
	@Override
	protected AbstractContainerMenu getMenu(
		int menuId,
		@NotNull Inventory inventory,
		@NotNull ContainerLevelAccess containerLevelAccess ) {
		
		return new TableSawDiamondMenu( menuId, inventory, containerLevelAccess );
	}
	
	@NotNull
	@Override
	protected Component getContainerName() {
		
		return CONTAINER_TITLE;
	}
}
