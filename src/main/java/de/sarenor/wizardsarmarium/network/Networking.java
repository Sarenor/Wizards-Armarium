package de.sarenor.wizardsarmarium.network;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import lombok.extern.log4j.Log4j2;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Log4j2
public class Networking {
    public static SimpleChannel INSTANCE;

    private static int ID = 0;

    public static int nextID() {
        return ID++;
    }

    public static void registerMessages() {
        log.info("ArsInstrumentum: Registering Network Packets");
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(WizardsArmarium.MODID, "network"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(nextID(),
                WizardsArmariumSwitchMessage.class,
                WizardsArmariumSwitchMessage::toBytes,
                WizardsArmariumSwitchMessage::new,
                WizardsArmariumSwitchMessage::handle);

    }
}
