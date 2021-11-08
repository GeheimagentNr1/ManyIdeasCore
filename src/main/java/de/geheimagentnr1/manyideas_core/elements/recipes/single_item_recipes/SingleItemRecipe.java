package de.geheimagentnr1.manyideas_core.elements.recipes.single_item_recipes;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nonnull;


public abstract class SingleItemRecipe implements Recipe<Container> {
	
	
	protected final Ingredient ingredient;
	
	//package-private
	final ItemStack result;
	
	private final RecipeType<?> type;
	
	private final RecipeSerializer<?> serializer;
	
	private final ResourceLocation id;
	
	private final String group;
	
	protected SingleItemRecipe(
		RecipeType<?> _type,
		RecipeSerializer<?> _serializer,
		ResourceLocation _id,
		String _group,
		Ingredient _ingredient,
		ItemStack _result ) {
		
		type = _type;
		serializer = _serializer;
		id = _id;
		group = _group;
		ingredient = _ingredient;
		result = _result;
	}
	
	@Nonnull
	@Override
	public RecipeType<?> getType() {
		
		return type;
	}
	
	@Nonnull
	@Override
	public RecipeSerializer<?> getSerializer() {
		
		return serializer;
	}
	
	@Nonnull
	@Override
	public ResourceLocation getId() {
		
		return id;
	}
	
	@Nonnull
	@Override
	public String getGroup() {
		
		return group;
	}
	
	@Nonnull
	@Override
	public ItemStack getResultItem() {
		
		return result;
	}
	
	@Nonnull
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
	
	@Nonnull
	@Override
	public ItemStack assemble( @Nonnull Container inv ) {
		
		return result.copy();
	}
	
	
	public Ingredient getIngredient() {
		
		return ingredient;
	}
}
