package de.geheimagentnr1.manyideascore.setup;

import de.geheimagentnr1.manyideascore.ManyIdeasCore;
import de.geheimagentnr1.manyideascore.config.ModConfig;
import de.geheimagentnr1.manyideascore.elements.commands.ModArgumentTypes;
import de.geheimagentnr1.manyideascore.elements.item_groups.CoreItemGroup;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeSerializers;
import de.geheimagentnr1.manyideascore.elements.recipes.RecipeTypes;
import de.geheimagentnr1.manyideascore.elements.recipes.dyed_recipes.ColorIngredientSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;


public class ModSetup {
	
	
	@SuppressWarnings( "PublicField" )
	public final CoreItemGroup coreItemGroup = new CoreItemGroup();
	
	public ModSetup() {
		
		ModConfig.load();
		ModArgumentTypes.registerArgumentTypes();
		RecipeTypes.init();
		RecipeSerializers.init();
		CraftingHelper.register( new ResourceLocation( ManyIdeasCore.MODID + ":" + "color" ),
			ColorIngredientSerializer.INSTANCE );
	}
}
