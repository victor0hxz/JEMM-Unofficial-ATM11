package giselle.jei_mekanism_multiblocks.client.jei.category;

import java.util.function.Consumer;

import giselle.jei_mekanism_multiblocks.client.gui.CheckBoxWidget;
import giselle.jei_mekanism_multiblocks.client.gui.IntSliderWidget;
import giselle.jei_mekanism_multiblocks.client.gui.IntSliderWithButtons;
import giselle.jei_mekanism_multiblocks.client.jei.MultiblockCategory;
import giselle.jei_mekanism_multiblocks.client.jei.MultiblockWidget;
import giselle.jei_mekanism_multiblocks.client.jei.ResultWidget;
import mekanism.common.Mekanism;
import mekanism.common.MekanismLang;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.util.text.TextUtils;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class MatrixCategory extends MultiblockCategory<MatrixCategory.MatrixWidget>
{
	public MatrixCategory(IGuiHelper helper)
	{
		super(helper, Mekanism.rl("matrix"), MatrixWidget.class, MekanismLang.MATRIX.translate(), new ItemStack(MekanismBlocks.INDUCTION_PORT));
	}

	@Override
	protected void getRecipeCatalystItemStacks(Consumer<ItemStack> consumer)
	{
		super.getRecipeCatalystItemStacks(consumer);
		consumer.accept(new ItemStack(MekanismBlocks.INDUCTION_CASING));
		consumer.accept(new ItemStack(MekanismBlocks.INDUCTION_PORT));
		consumer.accept(new ItemStack(MekanismBlocks.STRUCTURAL_GLASS));

		consumer.accept(new ItemStack(MekanismBlocks.BASIC_INDUCTION_CELL));
		consumer.accept(new ItemStack(MekanismBlocks.BASIC_INDUCTION_PROVIDER));
		consumer.accept(new ItemStack(MekanismBlocks.ADVANCED_INDUCTION_CELL));
		consumer.accept(new ItemStack(MekanismBlocks.ADVANCED_INDUCTION_PROVIDER));
		consumer.accept(new ItemStack(MekanismBlocks.ELITE_INDUCTION_CELL));
		consumer.accept(new ItemStack(MekanismBlocks.ELITE_INDUCTION_PROVIDER));
		consumer.accept(new ItemStack(MekanismBlocks.ULTIMATE_INDUCTION_CELL));
		consumer.accept(new ItemStack(MekanismBlocks.ULTIMATE_INDUCTION_PROVIDER));
        for(var tier:giselle.jei_mekanism_multiblocks.client.jei.MatrixTiers.available().stream().skip(4).toList()){consumer.accept(tier.cell().copy());consumer.accept(tier.provider().copy());}
	}

	public static class MatrixWidget extends MultiblockWidget
	{
		protected CheckBoxWidget useStructuralGlassCheckBox;
		protected IntSliderWithButtons portsWidget;
        private java.util.List<giselle.jei_mekanism_multiblocks.client.jei.MatrixTiers.Tier> tiers;
        private IntSliderWithButtons cellTierWidget, providerTierWidget, cellsWidget, providersWidget;
        private IntSliderWithButtons tierSelector(String translationKey, boolean cells){
            return new IntSliderWithButtons(0,0,0,0,translationKey,0,0,tiers.size()-1){
                @Override protected String getDisplayValue(){int index=Math.max(0,Math.min(tiers.size()-1,getSlider().getValue()));return (cells?tiers.get(index).cell():tiers.get(index).provider()).getHoverName().getString();}
            };
        }


		public MatrixWidget()
		{

		}

		@Override
		protected void collectOtherConfigs(Consumer<AbstractWidget> consumer)
		{
			super.collectOtherConfigs(consumer);

			consumer.accept(this.useStructuralGlassCheckBox = new CheckBoxWidget(0, 0, 0, 0, Component.translatable("text.jei_mekanism_multiblocks.specs.use_things", new ItemStack(MekanismBlocks.STRUCTURAL_GLASS).getHoverName()), true));
			this.useStructuralGlassCheckBox.addSelectedChangedHandler(this::onUseStructuralGlassChanged);

			consumer.accept(this.portsWidget = new IntSliderWithButtons(0, 0, 0, 0, "text.jei_mekanism_multiblocks.specs.ports", 0, 2, 0));
			this.portsWidget.getSlider().addValueChangeHanlder(this::onPortsChanged);

            tiers=giselle.jei_mekanism_multiblocks.client.jei.MatrixTiers.available();
            consumer.accept(cellTierWidget=tierSelector("text.jei_mekanism_multiblocks.specs.cell_tier",true));
            consumer.accept(providerTierWidget=tierSelector("text.jei_mekanism_multiblocks.specs.provider_tier",false));
            consumer.accept(cellsWidget=new IntSliderWithButtons(0,0,0,0,"text.jei_mekanism_multiblocks.specs.cells",0,0,getDimensionInnerVolume()));
            consumer.accept(providersWidget=new IntSliderWithButtons(0,0,0,0,"text.jei_mekanism_multiblocks.specs.providers",0,0,getDimensionInnerVolume()));
            cellTierWidget.getSlider().addValueChangeHanlder(v -> markNeedUpdate());
            providerTierWidget.getSlider().addValueChangeHanlder(v -> markNeedUpdate());
            cellsWidget.getSlider().addValueChangeHanlder(v -> {updateInteriorLimits();markNeedUpdate();});
            providersWidget.getSlider().addValueChangeHanlder(v -> {updateInteriorLimits();markNeedUpdate();});
            this.updatePortsSliderLimit();
        }

		@Override
		protected void collectCost(ICostConsumer consumer)
		{
			super.collectCost(consumer);

			int corners = this.getCornerBlocks();
			int sides = this.getSideBlocks();

			int ports = this.getPortCount();
			sides -= ports;

			int casing = 0;
			int structuralGlasses = 0;

			if (this.isUseStruturalGlass())
			{
				casing = corners;
				structuralGlasses = sides;
			}
			else
			{
				casing = corners + sides;
				structuralGlasses = 0;
			}

			consumer.accept(new ItemStack(MekanismBlocks.INDUCTION_CASING, casing));
			consumer.accept(new ItemStack(MekanismBlocks.INDUCTION_PORT, ports));
			consumer.accept(new ItemStack(MekanismBlocks.STRUCTURAL_GLASS, structuralGlasses));
            consumer.accept(tiers.get(cellTierWidget.getSlider().getValue()).cell().copyWithCount(cellsWidget.getSlider().getValue()));
            consumer.accept(tiers.get(providerTierWidget.getSlider().getValue()).provider().copyWithCount(providersWidget.getSlider().getValue()));
		}

		@Override
		protected void collectResult(Consumer<AbstractWidget> consumer)
		{
			super.collectResult(consumer);

			int innerVolume = this.getDimensionInnerVolume();
            long capacity=giselle.jei_mekanism_multiblocks.client.jei.MatrixTiers.multiply(tiers.get(cellTierWidget.getSlider().getValue()).capacity().getAsLong(),cellsWidget.getSlider().getValue());
            long output=giselle.jei_mekanism_multiblocks.client.jei.MatrixTiers.multiply(tiers.get(providerTierWidget.getSlider().getValue()).output().getAsLong(),providersWidget.getSlider().getValue());
            consumer.accept(new ResultWidget(Component.translatable("text.jei_mekanism_multiblocks.result.matrix_capacity"),Component.literal(TextUtils.format(capacity)+" J")));
            consumer.accept(new ResultWidget(Component.translatable("text.jei_mekanism_multiblocks.result.matrix_output"),Component.literal(TextUtils.format(output)+" J/t")));
			consumer.accept(new ResultWidget(Component.translatable("text.jei_mekanism_multiblocks.result.inner_volume"), Component.translatable("text.jei_mekanism_multiblocks.result.blocks", TextUtils.format(innerVolume))));
		}

		@Override
		protected void onDimensionChanged()
		{
			super.onDimensionChanged();

			this.updatePortsSliderLimit();
            updateInteriorLimits();
		}

        private void updateInteriorLimits(){
            if(cellsWidget==null || providersWidget==null)return;
            int volume=getDimensionInnerVolume();
            cellsWidget.getSlider().setMaxValue(Math.max(0,volume-providersWidget.getSlider().getValue()));
            providersWidget.getSlider().setMaxValue(Math.max(0,volume-cellsWidget.getSlider().getValue()));
        }

		protected void onUseStructuralGlassChanged(boolean useStructuralGlass)
		{
			this.markNeedUpdate();
		}

		public void updatePortsSliderLimit()
		{
			IntSliderWidget portsSlider = this.portsWidget.getSlider();
			int ports = portsSlider.getValue();
			portsSlider.setMaxValue(this.getSideBlocks());
			portsSlider.setValue(ports);
		}

		protected void onPortsChanged(int ports)
		{
			this.markNeedUpdate();
		}

		public void setCellTier(int value){cellTierWidget.getSlider().setValue(value);}
        public void setProviderTier(int value){providerTierWidget.getSlider().setValue(value);}
        public void setCells(int value){cellsWidget.getSlider().setValue(value);}
        public void setProviders(int value){providersWidget.getSlider().setValue(value);}
        public int getTierCount(){return tiers.size();}
        public boolean isUseStruturalGlass()
		{
			return this.useStructuralGlassCheckBox.isSelected();
		}

		public void setUseStructuralGlass(boolean useStructuralGlass)
		{
			this.useStructuralGlassCheckBox.setSelected(useStructuralGlass);
		}

		public int getPortCount()
		{
			return this.portsWidget.getSlider().getValue();
		}

		public void setPortCount(int portCount)
		{
			this.portsWidget.getSlider().setValue(portCount);
		}

		@Override
		public int getDimensionWidthMin()
		{
			return 3;
		}

		@Override
		public int getDimensionWidthMax()
		{
			return 18;
		}

		@Override
		public int getDimensionLengthMin()
		{
			return 3;
		}

		@Override
		public int getDimensionLengthMax()
		{
			return 18;
		}

		@Override
		public int getDimensionHeightMin()
		{
			return 3;
		}

		@Override
		public int getDimensionHeightMax()
		{
			return 18;
		}

	}

}
