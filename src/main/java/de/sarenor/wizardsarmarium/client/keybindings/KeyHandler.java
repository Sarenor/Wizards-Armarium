package de.sarenor.wizardsarmarium.client.keybindings;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import de.sarenor.wizardsarmarium.network.Networking;
import de.sarenor.wizardsarmarium.network.WizardsArmariumSwitchMessage;
import lombok.extern.log4j.Log4j2;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

import static de.sarenor.wizardsarmarium.setup.Registration.WIZARDS_ARMARIUM;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = WizardsArmarium.MODID)
@Log4j2
public class KeyHandler {
    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void checkKeysPressed(int key) {
        Player player = MINECRAFT.player;
        if (player != null && key == ModKeyBindings.SWITCH_ARMARIUM_SLOT.getKey().getValue()) {
            if (CuriosApi.getCuriosHelper().findEquippedCurio(WIZARDS_ARMARIUM.get(), player).isPresent()) {
                Networking.INSTANCE.sendToServer(new WizardsArmariumSwitchMessage());
            }
        }
    }

    @SubscribeEvent
    public static void mouseEvent(final InputEvent.MouseInputEvent event) {
        if (MINECRAFT.player == null || MINECRAFT.screen != null || event.getAction() != 1) {
            return;
        }
        checkKeysPressed(event.getButton());
    }

    @SubscribeEvent
    public static void keyEvent(final InputEvent.KeyInputEvent event) {
        if (MINECRAFT.player == null || MINECRAFT.screen != null || event.getAction() != 1) {
            return;
        }
        checkKeysPressed(event.getKey());

    }

}
