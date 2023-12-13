package de.geheimagentnr1.manyideas_core.elements.recipes.dyed_recipes;

import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeSerializersRegisterFactory;
import de.geheimagentnr1.manyideas_core.elements.recipes.ModRecipeTypesRegisterFactory;
import lombok.Getter;
import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;


@Getter
public class DyedShapedRecipe extends DyedRecipe {
	
	
	@NotNull
	public static final String registry_name = "dyed_shaped";
	
	private final ShapedRecipePattern pattern;
	
	//package-private
	DyedShapedRecipe(
		@NotNull ShapedRecipePattern _pattern,
		@NotNull ItemStack _result ) {
		
		super( _pattern.ingredients(), _result );
		pattern = _pattern;
	}
	
	@Override
	public boolean matches( @NotNull CraftingContainer inv, @NotNull Level level ) {
		
		if( findMatchingColor( inv ).isEmpty() ) {
			return false;
		}
		return pattern.matches( inv );
	}
	
	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canCraftInDimensions( int width, int height ) {
		
		return width >= pattern.width() && height >= pattern.height();
	}
	
	@NotNull
	@Override
	public RecipeType<?> getType() {
		
		return ModRecipeTypesRegisterFactory.DYED;
	}
	
	@NotNull
	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return ModRecipeSerializersRegisterFactory.DYED_SHAPED;
	}
	
	@NotNull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		return pattern.ingredients();
	}
}
