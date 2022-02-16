package de.sarenor.wizardsarmarium.client.keybindings;

import com.mojang.blaze3d.platform.InputConstants;
import de.sarenor.wizardsarmarium.WizardsArmarium;
import lombok.extern.log4j.Log4j2;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;


@Mod.EventBusSubscriber(modid = WizardsArmarium.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
@Log4j2
public class ModKeyBindings {

    public static final String SWITCH_ARMARIUM_SLOT_ID = "key.wizards_armarium.switch_armarium_slot";
    public static final String CATEGORY = "key.category.wizards_armarium.general";
    public static final KeyMapping SWITCH_ARMARIUM_SLOT = new KeyMapping("key.ars_nouveau.switch_armarium_slot", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, CATEGORY);

    @SubscribeEvent
    public static void registerKeyBindings(final FMLClientSetupEvent event) {
        log.info("ArsInstrumentum: Keybindings started");
        ClientRegistry.registerKeyBinding(SWITCH_ARMARIUM_SLOT);
        log.info("ArsInstrumentum: Keybindings ended");
    }
}