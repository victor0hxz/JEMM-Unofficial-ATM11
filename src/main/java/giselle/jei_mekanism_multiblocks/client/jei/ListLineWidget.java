package giselle.jei_mekanism_multiblocks.client.jei;

import java.util.List;

import giselle.jei_mekanism_multiblocks.client.GuiHelper;
import giselle.jei_mekanism_multiblocks.client.gui.ListWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;

public class ListLineWidget extends ListWidget
{
	public ListLineWidget(int pX, int pY, int pWidth, int pHeight, int itemHeight)
	{
		super(pX, pY, pWidth, pHeight, itemHeight);
	}

	@Override
	protected void onRenderWidget(List<AbstractWidget> widgets, AbstractWidget widget, GuiGraphicsExtractor pGuiGraphicsExtractor, int childMouseX, int childMouseY, float pPartialTicks)
	{
		super.onRenderWidget(widgets, widget, pGuiGraphicsExtractor, childMouseX, childMouseY, pPartialTicks);

		if (widgets == this.getChildren() && widget.visible)
		{
			GuiHelper.fillRectagleBlack(pGuiGraphicsExtractor, 0, widget.getY() + widget.getHeight(), this.getWidth(), 1);
		}

	}

}
