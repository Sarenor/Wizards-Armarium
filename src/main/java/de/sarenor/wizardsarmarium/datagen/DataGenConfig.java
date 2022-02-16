package de.sarenor.wizardsarmarium.datagen;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import lombok.extern.log4j.Log4j2;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = WizardsArmarium.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Log4j2
public class DataGenConfig {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        log.info("Wizards Armarium: Data Generation started.");
        generator.addProvider(new Recipes(generator));
        generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(new LanguageProvider(generator, "en_us"));
        log.info("Wizards Armarium: Data Generation ended.");
    }
}
