package giselle.jei_mekanism_multiblocks.client;

import java.util.Arrays;
import java.util.List;

import org.joml.Matrix3x2fStack;

import giselle.jei_mekanism_multiblocks.client.gui.TextAlignment;
import giselle.jei_mekanism_multiblocks.common.JEI_MekanismMultiblocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class GuiHelper
{
	public static final Identifier WIDGETS_LOCATION = JEI_MekanismMultiblocks.rl("textures/gui/widgets.png");

	public static void renderComponentTooltip(GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY, Component... tooltip)
	{
		Minecraft minecraft = Minecraft.getInstance();

		if (minecraft.screen != null && tooltip.length > 0)
		{
			pGuiGraphicsExtractor.setComponentTooltipForNextFrame(minecraft.font, Arrays.asList(tooltip), pMouseX, pMouseY);
		}

	}

	public static void renderComponentTooltip(GuiGraphicsExtractor pGuiGraphicsExtractor, int pMouseX, int pMouseY, List<Component> tooltip)
	{
		Minecraft minecraft = Minecraft.getInstance();

		if (minecraft.screen != null && tooltip.size() > 0)
		{
			pGuiGraphicsExtractor.setComponentTooltipForNextFrame(minecraft.font, tooltip, pMouseX, pMouseY);
		}

	}

	public static void fillRectagleBlack(GuiGraphicsExtractor pGuiGraphicsExtractor, int x, int y, int width, int height)
	{
		pGuiGraphicsExtractor.fill(x, y, x + width, y + height, 0xFF000000);
	}

	public static void fillRectagle(GuiGraphicsExtractor pGuiGraphicsExtractor, int x, int y, int width, int height, float r, float g, float b, float a)
	{
		int color = net.minecraft.util.ARGB.colorFromFloat(a, r, g, b);
		pGuiGraphicsExtractor.fill(x, y, x + width, y + height, color);
	}

	public static void drawScaledText(GuiGraphicsExtractor pGuiGraphicsExtractor, Component text, float x, float y, float width, int color, boolean shadow)
	{
		drawScaledText(pGuiGraphicsExtractor, text, x, y, width, color, shadow, TextAlignment.LEFT);
	}

	public static void drawScaledText(GuiGraphicsExtractor pGuiGraphicsExtractor, Component text, float x, float y, float width, int color, boolean shadow, TextAlignment alignment)
	{
		Minecraft minecraft = Minecraft.getInstance();
		Font font = minecraft.font;
		int textWidth = font.width(text);
		float scale = Math.min(width / textWidth, 1.0F);
		Matrix3x2fStack pose = pGuiGraphicsExtractor.pose();
		pose.pushMatrix();
		pose.scale(scale, scale);

		float scaledX = (x + (float) alignment.align(width, textWidth * scale)) / scale;
		float scaledY = y / scale + (1.0F - scale) * font.lineHeight;

		pGuiGraphicsExtractor.text(font, text, (int) scaledX, (int) scaledY, (color & 0xFF000000) == 0 ? color | 0xFF000000 : color, shadow);

		pose.popMatrix();
	}

    public static void blit(GuiGraphicsExtractor g, Identifier id, int x, int y, int w, int h,
                           float u, float v, int regionW, int regionH, int texW, int texH) {
        g.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, id, x, y, u, v, w, h, regionW, regionH, texW, texH);
    }

	public static void blitButton(GuiGraphicsExtractor pGuiGraphicsExtractor, int x, int y, int width, int height, boolean active, boolean hovered)
	{
		int i = active ? hovered ? 2 : 1 : 0;
		GuiHelper.blit9Patch(pGuiGraphicsExtractor, WIDGETS_LOCATION, x, y, width, height, i * 20, 52, 20, 20, 2, 2, 2, 2);
	}

	public static void blit9Patch(GuiGraphicsExtractor pGuiGraphicsExtractor, Identifier pAtlasLocation, int x, int y, int width, int height, int textureX, int textureY, int textureW, int textureH, int uL, int vT, int uR, int vB)
	{
		blit9Patch(pGuiGraphicsExtractor, pAtlasLocation, x, y, width, height, textureX, textureY, textureW, textureH, uL, vT, uR, vB, 256, 256);
	}

	public static void blit9Patch(GuiGraphicsExtractor pGuiGraphicsExtractor, Identifier pAtlasLocation, int x, int y, int width, int height, int textureX, int textureY, int textureW, int textureH, int uL, int vT, int uR, int vB, int textureWidth, int textureHeight)
	{
		uL = Math.min(uL, width / 2);
		uR = Math.min(uR, width / 2);
		vT = Math.min(vT, height / 2);
		vB = Math.min(vB, height / 2);

		int inL = x + uL;
		int inR = x + width - uR;
		int inT = y + vT;
		int inB = y + height - vB;
		int inW = width - uL - uR;
		int inH = height - vT - vB;

		int textureInL = textureX + uL;
		int textureInR = textureX + textureW - uR;
		int textureInT = textureY + vT;
		int textureInB = textureY + textureH - vB;
		int textureInW = textureInR - textureInL;
		int textureInH = textureInB - textureInT;

		// Left -Top
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, x, y, uL, vT, textureX, textureY, uL, vT, textureWidth, textureHeight);
		// Right - Top
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, inR, y, uR, vT, textureInR, textureY, uR, vT, textureWidth, textureHeight);
		// Right - Bottom
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, x, inB, uL, vB, textureX, textureInB, uL, vB, textureWidth, textureHeight);
		// Left - Bottom
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, inR, inB, uR, vB, textureInR, textureInB, uR, vB, textureWidth, textureHeight);

		// Top
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, inL, y, inW, vT, textureInL, textureY, textureInW, vT, textureWidth, textureHeight);
		// Right
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, inR, inT, uR, inH, textureInR, textureInT, uR, textureInH, textureWidth, textureHeight);
		// Bottom
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, inL, inB, inW, vT, textureInL, textureInB, textureInW, vB, textureWidth, textureHeight);
		// Left
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, x, inT, uL, inH, textureX, textureInT, uL, textureInH, textureWidth, textureHeight);

		// Inner
		GuiHelper.blit(pGuiGraphicsExtractor, pAtlasLocation, inL, inT, inW, inH, textureInL, textureInT, textureInW, textureInH, textureWidth, textureHeight);
	}

	private GuiHelper()
	{

	}

}
