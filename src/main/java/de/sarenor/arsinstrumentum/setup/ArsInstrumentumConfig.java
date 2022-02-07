package de.sarenor.arsinstrumentum.setup;

import com.hollingsworth.arsnouveau.api.RegistryHelper;
import de.sarenor.arsinstrumentum.ArsInstrumentum;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber
public class ArsInstrumentumConfig {

    public static void registerGlyphConfigs(){
        RegistryHelper.generateConfig(ArsInstrumentum.MODID, ArsNouveauRegistry.registeredSpells);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) { }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) { }
}
