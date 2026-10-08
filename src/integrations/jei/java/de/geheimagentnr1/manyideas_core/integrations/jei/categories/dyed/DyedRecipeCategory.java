package de.geheimagentnr1.manyideas_core.integrations.jei.categories.dyed;

import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import de.geheimagentnr1.manyideas_core.elements.blocks.ModBlocksRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes.DyedRecipe;
import de.geheimagentnr1.manyideas_core.integrations.jei.categories.template.JeiRecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class DyedRecipeCategory extends JeiRecipeCategory<JeiDyedRecipe> {
	
	
	@NotNull
	public static final RecipeType<JeiDyedRecipe> DYED = RecipeType.create(
		ManyIdeasCore.MODID,
		DyedRecipe.registry_name,
		JeiDyedRecipe.class
	);
	
	@NotNull
	private final IDrawable arrow;
	
	@NotNull
	private final IDrawable slot;
	
	public DyedRecipeCategory( @NotNull IGuiHelper guiHelper ) {
		
		super(
			guiHelper,
			DYED,
			ModBlocksRegisterFactory.DYE_CRAFTING_TABLE,
			guiHelper.createBlankDrawable( 116, 54 )
		);
		arrow = guiHelper.getRecipeArrow();
		slot = guiHelper.getSlotDrawable();
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
					builder.addSlot( RecipeIngredientRole.INPUT, j * 18 + 1, i * 18 + 1 )
						.addItemStacks( input.get( j + i * 3 ) );
				}
			}
		}
		builder.addSlot( RecipeIngredientRole.OUTPUT, 95, 19 )
			.setOutputSlotBackground()
			.addItemStack( recipe.getResult() );
	}
	
	@Override
	public void draw(
		@NotNull JeiDyedRecipe recipe,
		@NotNull IRecipeSlotsView recipeSlotsView,
		@NotNull GuiGraphics guiGraphics,
		double mouseX,
		double mouseY ) {
		
		super.draw( recipe, recipeSlotsView, guiGraphics, mouseX, mouseY );
		for( int i = 0; i < 3; i++ ) {
			for( int j = 0; j < 3; j++ ) {
				slot.draw( guiGraphics, j * 18, i * 18 );
			}
		}
		arrow.draw( guiGraphics, 61, 19 );
	}
}
