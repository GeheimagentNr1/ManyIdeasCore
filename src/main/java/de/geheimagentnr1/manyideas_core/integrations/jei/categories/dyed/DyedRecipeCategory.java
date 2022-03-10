package de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocks;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.JeiRecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class DyedRecipeCategory extends JeiRecipeCategory<JeiDyedRecipe> {
	
	
	public static final RecipeType<JeiDyedRecipe> DYED = RecipeType.create(
		ManyIdeasCore.MODID,
		DyedRecipe.registry_name,
		JeiDyedRecipe.class
	);
	
	private static final ResourceLocation texture = new ResourceLocation( "jei", "textures/gui/gui_vanilla.png" );
	
	public DyedRecipeCategory( IGuiHelper guiHelper ) {
		
		super( guiHelper, DYED, ModBlocks.DYE_CRAFTING_TABLE, guiHelper.createDrawable( texture, 0, 60, 116, 54 ) );
	}
	
	@Override
	public void setRecipe(
		@NotNull IRecipeLayoutBuilder builder,
		@NotNull JeiDyedRecipe recipe,
		@NotNull IFocusGroup focuses ) {
		
		List<List<ItemStack>> input = recipe.getInputs();
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				if( j + i * 3 < input.size() ) {
					builder.addSlot( RecipeIngredientRole.INPUT, j * 18, i * 18 )
						.addItemStacks( input.get( j + i * 3 ) );
				}
			}
		}
		builder.addSlot( RecipeIngredientRole.OUTPUT, 94, 18 )
			.addItemStack( recipe.getResult() );
	}
}
