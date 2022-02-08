package de.sarenor.arsinstrumentum.datagen;

import de.sarenor.arsinstrumentum.ArsInstrumentum;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = ArsInstrumentum.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Slf4j
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        log.info("Ars Instrumentum: Data Generation started.");
        generator.addProvider(new ArsInstrumentumApparatusRecipes(generator));
        generator.addProvider(new ArsInstrumentumLanguageProvider(generator, "en_us"));
        generator.addProvider(new ArsInstrumentumItemModels(generator, event.getExistingFileHelper()));
        log.info("Ars Instrumentum: Data Generation ended.");
    }
}
