package de.geheimagentnr1.manyideas_core.elements.blocks.table_saws;

import com.mojang.blaze3d.platform.GlStateManager;
import de.geheimagentnr1.manyideas_core.ManyIdeasCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Objects;


@OnlyIn( Dist.CLIENT )
public class TableSawScreen extends ContainerScreen<TableSawContainer> {
	
	
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(
		ManyIdeasCore.MODID,
		"textures/gui/table_saws/table_saw_gui.png"
	);
	
	private float sliderProgress;
	
	/**
	 * Is {@code true} if the player clicked on the scroll wheel in the GUI.
	 */
	private boolean clickedOnSroll;
	
	/**
	 * The index of the first recipe to display.
	 * The number of recipes displayed at any time is 12 (4 recipes per row, and 3 rows). If the player scrolled down
	 * one
	 * row, this value would be 4 (representing the index of the first slot on the second row).
	 */
	private int recipeIndexOffset;
	
	private boolean hasItemsInInputSlot;
	
	public TableSawScreen( TableSawContainer containerIn, PlayerInventory inv, ITextComponent titleIn ) {
		
		super( containerIn, inv, titleIn );
		containerIn.setInventoryUpdateListener( this::onInventoryUpdate );
	}
	
	@SuppressWarnings( "ParameterNameDiffersFromOverriddenParameter" )
	@Override
	public void render( int mouseX, int mouseY, float partialTicks ) {
		
		super.render( mouseX, mouseY, partialTicks );
		renderHoveredToolTip( mouseX, mouseY );
	}
	
	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY ) {
		
		font.drawString( title.getFormattedText(), 8.0F, 4.0F, 4210752 );
		font.drawString( playerInventory.getDisplayName().getFormattedText(), 8.0F, ySize - 94, 4210752 );
	}
	
	/**
	 * Draws the background layer of this container (behind the items).
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ) {
		
		renderBackground();
		GlStateManager.color4f( 1.0F, 1.0F, 1.0F, 1.0F );
		Objects.requireNonNull( minecraft ).getTextureManager().bindTexture( BACKGROUND_TEXTURE );
		int i = guiLeft;
		int j = guiTop;
		blit( i, j, 0, 0, xSize, ySize );
		int k = (int)( 41.0F * sliderProgress );
		blit( i + 119, j + 15 + k, 176 + ( canScroll() ? 0 : 12 ), 0, 12, 15 );
		int l = guiLeft + 52;
		int i1 = guiTop + 14;
		int j1 = recipeIndexOffset + 12;
		drawRecipesBackground( mouseX, mouseY, l, i1, j1 );
		drawRecipesItems( l, i1, j1 );
	}
	
	private void drawRecipesBackground( int mouseX, int mouseY, int left, int top, int recipeIndexOffsetMax ) {
		
		for( int i = recipeIndexOffset; i < recipeIndexOffsetMax && i < container.getRecipeListSize(); ++i ) {
			int j = i - recipeIndexOffset;
			int k = left + ( j % 4 << 4 );
			int l = j / 4;
			int i1 = top + l * 18 + 2;
			int j1 = ySize;
			if( i == container.getSelectedRecipe() ) {
				j1 += 18;
			} else {
				if( mouseX >= k && mouseY >= i1 && mouseX < k + 16 && mouseY < i1 + 18 ) {
					j1 += 36;
				}
			}
			blit( k, i1 - 1, 0, j1, 16, 18 );
		}
	}
	
	private void drawRecipesItems( int left, int top, int recipeIndexOffsetMax ) {
		
		List<TableSawRecipe> list = container.getRecipeList();
		
		for( int i = recipeIndexOffset; i < recipeIndexOffsetMax && i < container.getRecipeListSize(); ++i ) {
			int j = i - recipeIndexOffset;
			int k = left + ( j % 4 << 4 );
			int l = j / 4;
			int i1 = top + l * 18 + 2;
			Objects.requireNonNull( minecraft ).getItemRenderer().renderItemAndEffectIntoGUI( list.get( i )
				.getRecipeOutput(), k, i1 );
		}
	}
	
	@Override
	public boolean mouseClicked( double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_ ) {
		
		clickedOnSroll = false;
		if( hasItemsInInputSlot ) {
			int i = guiLeft + 52;
			int j = guiTop + 14;
			int k = recipeIndexOffset + 12;
			
			for( int l = recipeIndexOffset; l < k; ++l ) {
				int i1 = l - recipeIndexOffset;
				double d0 = p_mouseClicked_1_ - ( i + ( i1 % 4 << 4 ) );
				@SuppressWarnings( "IntegerDivisionInFloatingPointContext" )
				double d1 = p_mouseClicked_3_ - ( j + i1 / 4 * 18 );
				if( d0 >= 0.0D && d1 >= 0.0D && d0 < 16.0D && d1 < 18.0D &&
					container.enchantItem( Objects.requireNonNull( minecraft ).player, l ) ) {
					Minecraft.getInstance()
						.getSoundHandler()
						.play( SimpleSound.master( SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F ) );
					minecraft.playerController.sendEnchantPacket( container.windowId, l );
					return true;
				}
			}
			int i1 = guiLeft + 119;
			int i2 = guiTop + 9;
			if( p_mouseClicked_1_ >= i1 && p_mouseClicked_1_ < i1 + 12 && p_mouseClicked_3_ >= i2 &&
				p_mouseClicked_3_ < i2 + 54 ) {
				clickedOnSroll = true;
			}
		}
		return super.mouseClicked( p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_ );
	}
	
	@Override
	public boolean mouseDragged(
		double p_mouseDragged_1_,
		double p_mouseDragged_3_,
		int p_mouseDragged_5_,
		double p_mouseDragged_6_,
		double p_mouseDragged_8_ ) {
		
		if( clickedOnSroll && canScroll() ) {
			int i = guiTop + 14;
			int j = i + 54;
			sliderProgress = ( (float)p_mouseDragged_3_ - i - 7.5F ) / ( ( j - i ) - 15.0F );
			sliderProgress = MathHelper.clamp( sliderProgress, 0.0F, 1.0F );
			recipeIndexOffset = (int)( sliderProgress * getHiddenRows() + 0.5D ) << 2;
			return true;
		} else {
			return super.mouseDragged(
				p_mouseDragged_1_,
				p_mouseDragged_3_,
				p_mouseDragged_5_,
				p_mouseDragged_6_,
				p_mouseDragged_8_
			);
		}
	}
	
	@Override
	public boolean mouseScrolled( double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_ ) {
		
		if( canScroll() ) {
			int i = getHiddenRows();
			sliderProgress -= p_mouseScrolled_5_ / i;
			sliderProgress = MathHelper.clamp( sliderProgress, 0.0F, 1.0F );
			recipeIndexOffset = (int)( sliderProgress * i + 0.5D ) << 2;
		}
		return true;
	}
	
	private boolean canScroll() {
		
		return hasItemsInInputSlot && container.getRecipeListSize() > 12;
	}
	
	private int getHiddenRows() {
		
		return ( container.getRecipeListSize() + 4 - 1 ) / 4 - 3;
	}
	
	/**
	 * Called every time this screen's container is changed (is marked as dirty).
	 */
	private void onInventoryUpdate() {
		
		hasItemsInInputSlot = container.hasItemsinInputSlot();
		if( !hasItemsInInputSlot ) {
			sliderProgress = 0.0F;
			recipeIndexOffset = 0;
		}
	}
}