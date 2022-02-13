package de.sarenor.arsinstrumentum.datagen;

import de.sarenor.arsinstrumentum.ArsInstrumentum;
import de.sarenor.arsinstrumentum.items.ScrollOfSaveStarbuncle;
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
        add(SWITCH_ARMARIUM_SLOT_ID, "Switch Wizards Armarium");
        add(ScrollOfSaveStarbuncle.SAVED_CONFIGURATION, "Saved Starbuncle configuration");
        add(ScrollOfSaveStarbuncle.APPLIED_CONFIGURATION, "Applied Starbuncle configuration");
        add(ScrollOfSaveStarbuncle.CLEARED_CONFIGURATION, "Cleared Starbuncle configuration");
        log.info("ArsInstrumentum: AddTranslation ended");
    }
}
