package giselle.jei_mekanism_multiblocks.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import giselle.jei_mekanism_multiblocks.client.JEI_MekanismMultiblocks_Client;
import giselle.jei_mekanism_multiblocks.common.config.JEI_MekanismMultiblocks_Config;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(JEI_MekanismMultiblocks.MODID)
public class JEI_MekanismMultiblocks
{
	public static final String MODID = "jei_mekanism_multiblocks";
	public static final Logger LOGGER = LogManager.getLogger();

	public static boolean MekanismGeneratorsLoaded = false;

	public JEI_MekanismMultiblocks(IEventBus fml_bus, net.neoforged.fml.ModContainer container)
	{
		
		JEI_MekanismMultiblocks_Config.registerConfigs(container);

		if (FMLEnvironment.getDist().isClient())
		{
			JEI_MekanismMultiblocks_Client.init();
		}

		
		fml_bus.addListener(JEI_MekanismMultiblocks::onCommonSetup);
	}

	private static void onCommonSetup(FMLCommonSetupEvent e)
	{
		ModList modList = ModList.get();
		MekanismGeneratorsLoaded = modList.isLoaded("mekanismgenerators");
	}

	public static Identifier rl(String path)
	{
		return Identifier.fromNamespaceAndPath(MODID, path);
	}

}
