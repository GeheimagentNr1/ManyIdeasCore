package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import org.jetbrains.annotations.NotNull;


public abstract class SingleItemRecipe implements Recipe<SingleRecipeInput> {


	@NotNull
	protected final Ingredient ingredient;

	//package-private
	@NotNull
	final ItemStack result;

	@NotNull
	private final RecipeType<? extends Recipe<SingleRecipeInput>> type;

	@NotNull
	private final RecipeSerializer<? extends Recipe<SingleRecipeInput>> serializer;

	@NotNull
	private final String group;

	protected SingleItemRecipe(
		@NotNull RecipeType<? extends Recipe<SingleRecipeInput>> _type,
		@NotNull RecipeSerializer<? extends Recipe<SingleRecipeInput>> _serializer,
		@NotNull String _group,
		@NotNull Ingredient _ingredient,
		@NotNull ItemStack _result ) {

		type = _type;
		serializer = _serializer;
		group = _group;
		ingredient = _ingredient;
		result = _result;
	}

	@NotNull
	@Override
	public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {

		return type;
	}

	@NotNull
	@Override
	public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {

		return serializer;
	}

	@NotNull
	public String getGroup() {

		return group;
	}

	@NotNull
	public ItemStack getResultItem( @NotNull HolderLookup.Provider pRegistries ) {

		return result;
	}

	@NotNull
	public NonNullList<Ingredient> getIngredients() {

		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add( ingredient );
		return nonnulllist;
	}

	public boolean canCraftInDimensions( int width, int height ) {

		return true;
	}

	@NotNull
	@Override
	public RecipeBookCategory recipeBookCategory() {

		return RecipeBookCategories.STONECUTTER;
	}

	@NotNull
	@Override
	public PlacementInfo placementInfo() {

		NonNullList<Ingredient> list = NonNullList.create();
		list.add( ingredient );
		return PlacementInfo.create( list );
	}

	@NotNull
	@Override
	public ItemStack assemble(
		@NotNull SingleRecipeInput pCraftingContainer,
		@NotNull HolderLookup.Provider pRegistries ) {

		return result.copy();
	}


	@NotNull
	public Ingredient getIngredient() {

		return ingredient;
	}

	@NotNull
	public ItemStack getResult() {

		return result;
	}
}
