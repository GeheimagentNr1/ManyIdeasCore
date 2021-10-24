package de.geheimagentnr1.manyideas_core.setup;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.commands.ModArgumentTypes;
import de.geheimagentnr1.manyideas_core.elements.item_groups.ManyIdeasCoreItemGroup;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideas_core.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.ColorIngredientSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;


public class ModSetup {
	
	
	@SuppressWarnings( "PublicField" )
	public final ManyIdeasCoreItemGroup manyIdeasCoreItemGroup = new ManyIdeasCoreItemGroup();
	
	public ModSetup() {
		
		ModArgumentTypes.registerArgumentTypes();
		RecipeTypes.init();
		/*RecipeSerializers.init();
		CraftingHelper.register(
			new ResourceLocation( ManyIdeasCore.MODID + ":" + "color" ),
			ColorIngredientSerializer.INSTANCE
		);*/
	}
}
