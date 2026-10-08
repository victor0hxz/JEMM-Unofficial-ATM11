package giselle.jei_mekanism_multiblocks.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;

import giselle.jei_mekanism_multiblocks.client.GuiHelper;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public class ButtonWidget extends AbstractWidget
{
	private final List<IPressHandler> pressHandlers;

	public ButtonWidget(int pX, int pY, int pWidth, int pHeight, Component pMessage)
	{
		super(pX, pY, pWidth, pHeight, pMessage);
		this.pressHandlers = new ArrayList<>();
	}

	public void addPressHandler(IPressHandler handler)
	{
		this.pressHandlers.add(handler);
	}

    @Override
    public void onClick(MouseButtonEvent event, boolean doubleClick) { onPress(); }
    @Override
    public boolean keyPressed(KeyEvent event) {
        if (active && (event.key() == 32 || event.key() == 257 || event.key() == 335)) { onPress(); return true; }
        return false;
    }

	public void onPress()
	{
		for (IPressHandler handler : this.pressHandlers)
		{
			handler.onPress(this);
		}

	}

	@Override
	public void extractWidgetRenderState(GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY, float pPartialTicks)
	{

		GuiHelper.blitButton(pGuiGraphicsExtractor, this.getX(), this.getY(), this.width, this.height, this.active, this.isHoveredOrFocused());

		int j = getFGColor();
		GuiHelper.drawScaledText(pGuiGraphicsExtractor, this.getMessage(), this.getX(), this.getY() + (this.height - 8) / 2, this.width, j | Mth.ceil(this.alpha * 255.0F) << 24, true, TextAlignment.CENTER);
	}

	@Override
	protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput)
	{
		this.defaultButtonNarrationText(pNarrationElementOutput);
	}

	public interface IPressHandler
	{
		void onPress(AbstractWidget pButton);
	}

}
