package de.sarenor.arsinstrumentum.datagen;

import de.sarenor.arsinstrumentum.ArsInstrumentum;
import de.sarenor.arsinstrumentum.setup.Registration;
import lombok.extern.log4j.Log4j2;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

import static de.sarenor.arsinstrumentum.client.keybindings.ModKeyBindings.SWITCH_ARMARIUM_SLOT_ID;

@Log4j2
public class ArsInstrumentumLanguageProvider extends LanguageProvider {


    public ArsInstrumentumLanguageProvider(DataGenerator gen, String locale) {
        super(gen, ArsInstrumentum.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        log.info("ArsInstrumentum: AddTranslation started");
        add(Registration.WIZARDS_ARMARIUM.get(), "Wizards Armarium");
        add(Registration.SCROLL_OF_SAVE_STARBUNCLE.get(), "Scroll of Save Starbuncle");
        add(Registration.RUNIC_STORAGE_STONE.get(), "Runic Stone of Storage");
        add(SWITCH_ARMARIUM_SLOT_ID, "Switch Wizards Armarium");
        log.info("ArsInstrumentum: AddTranslation ended");
    }
}
