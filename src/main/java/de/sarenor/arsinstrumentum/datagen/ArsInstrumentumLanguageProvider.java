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
        add(SWITCH_ARMARIUM_SLOT_ID, "Switch Wizards Armarium");
        add("ars_instrumentum.page." + Registration.WIZARDS_ARMARIUM.get().getRegistryName(), """
                A wizard is always prepared for all eventualities.
                With the Wizards Armarium your wardrobe travels with you and allows you to switch between 3 different Armor and Hotbar configurations""");
        log.info("ArsInstrumentum: AddTranslation ended");
    }
}
