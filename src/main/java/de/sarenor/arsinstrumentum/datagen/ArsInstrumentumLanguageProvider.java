package de.sarenor.arsinstrumentum.datagen;

import de.sarenor.arsinstrumentum.ArsInstrumentum;
import de.sarenor.arsinstrumentum.setup.Registration;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

@Slf4j
public class ArsInstrumentumLanguageProvider extends LanguageProvider {

    public ArsInstrumentumLanguageProvider(DataGenerator gen, String locale) {
        super(gen, ArsInstrumentum.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        log.info("ArsInstrumentum: AddTranslation started");
        add(Registration.WIZARDS_ARMARIUM.get(), "Wizards Armarium");
        log.info("ArsInstrumentum: AddTranslation ended");
    }
}
