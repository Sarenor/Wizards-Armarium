package de.sarenor.wizardsarmarium.datagen;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import de.sarenor.wizardsarmarium.client.keybindings.ModKeyBindings;
import de.sarenor.wizardsarmarium.setup.Registration;
import lombok.extern.log4j.Log4j2;
import net.minecraft.data.DataGenerator;

import static de.sarenor.wizardsarmarium.client.keybindings.ModKeyBindings.SWITCH_ARMARIUM_SLOT_ID;

@Log4j2
public class LanguageProvider extends net.minecraftforge.common.data.LanguageProvider {


    public LanguageProvider(DataGenerator gen, String locale) {
        super(gen, WizardsArmarium.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        log.info("Wizards Armarium: AddTranslation started");
        add(Registration.WIZARDS_ARMARIUM.get(), "Wizards Armarium");
        add(SWITCH_ARMARIUM_SLOT_ID, "Switch Wizards Armarium");
        add(ModKeyBindings.CATEGORY, "Wizards Armarium");
        log.info("Wizards Armarium: AddTranslation ended");
    }
}
