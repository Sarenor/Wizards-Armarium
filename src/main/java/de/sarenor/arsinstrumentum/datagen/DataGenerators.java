package de.sarenor.arsinstrumentum.datagen;

import de.sarenor.arsinstrumentum.ArsInstrumentum;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = ArsInstrumentum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        LOGGER.info("Ars Instrumentum: Data Generation started.");
        generator.addProvider(new ArsInstrumentumRecipes(generator));
        LOGGER.info("Ars Instrumentum: Data Generation ended.");
    }
}
