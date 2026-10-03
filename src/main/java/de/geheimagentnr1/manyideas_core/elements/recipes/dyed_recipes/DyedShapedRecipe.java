package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import net.minecraft.world.item.ItemStackTemplate;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import lombok.Getter;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


@Getter
public class DyedShapedRecipe extends DyedRecipe {


	@NotNull
	public static final String registry_name = "dyed_shaped";

	private final ShapedRecipePattern pattern;

	//package-private
	DyedShapedRecipe(
		@NotNull ShapedRecipePattern _pattern,
		@NotNull ItemStackTemplate _result ) {

		super( toNonNullList( _pattern ), _result );
		pattern = _pattern;
	}

	@NotNull
	private static NonNullList<Ingredient> toNonNullList( @NotNull ShapedRecipePattern pattern ) {

		NonNullList<Ingredient> list = NonNullList.create();
		for( Optional<Ingredient> opt : pattern.ingredients() ) {
			opt.ifPresent( list::add );
		}
		return list;
	}

	@Override
	public boolean matches( @NotNull CraftingInput inv, @NotNull Level level ) {

		if( findMatchingColor( inv ).isEmpty() ) {
			return false;
		}
		return pattern.matches( inv );
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	public boolean canCraftInDimensions( int width, int height ) {

		return width >= pattern.width() && height >= pattern.height();
	}

	@NotNull
	@Override
	public RecipeType<? extends Recipe<CraftingInput>> getType() {

		return ModRecipeTypesRegisterFactory.DYED;
	}

	@NotNull
	@Override
	public RecipeSerializer<? extends Recipe<CraftingInput>> getSerializer() {

		return ModRecipeSerializersRegisterFactory.DYED_SHAPED;
	}

	@NotNull
	public NonNullList<Ingredient> getIngredients() {

		return toNonNullList( pattern );
	}

	@NotNull
	@Override
	public PlacementInfo placementInfo() {

		return PlacementInfo.create( toNonNullList( pattern ) );
	}
}
