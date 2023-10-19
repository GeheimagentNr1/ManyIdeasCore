package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;


public abstract class SingleItemRecipe implements Recipe<Container> {
	
	
	@NotNull
	protected final Ingredient ingredient;
	
	//package-private
	@NotNull
	final ItemStack result;
	
	@NotNull
	private final RecipeType<?> type;
	
	@NotNull
	private final RecipeSerializer<?> serializer;
	
	@NotNull
	private final String group;
	
	protected SingleItemRecipe(
		@NotNull RecipeType<?> _type,
		@NotNull RecipeSerializer<?> _serializer,
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
	public RecipeType<?> getType() {
		
		return type;
	}
	
	@NotNull
	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return serializer;
	}
	
	@NotNull
	@Override
	public String getGroup() {
		
		return group;
	}
	
	@NotNull
	@Override
	public ItemStack getResultItem( @NotNull RegistryAccess registryAccess ) {
		
		return result;
	}
	
	@NotNull
	@Override
	public NonNullList<Ingredient> getIngredients() {
		
		NonNullList<Ingredient> nonnulllist = NonNullList.create();
		nonnulllist.add( ingredient );
		return nonnulllist;
	}
	
	@Override
	public boolean canCraftInDimensions( int width, int height ) {
		
		return true;
	}
	
	@NotNull
	@Override
	public ItemStack assemble( @NotNull Container container, @NotNull RegistryAccess registryAccess ) {
		
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
