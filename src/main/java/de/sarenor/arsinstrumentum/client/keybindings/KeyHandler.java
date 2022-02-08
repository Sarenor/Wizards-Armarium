package de.sarenor.arsinstrumentum.client.keybindings;

import com.hollingsworth.arsnouveau.ArsNouveau;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = ArsNouveau.MODID)
public class KeyHandler {
    private static final Minecraft MINECRAFT = Minecraft.getInstance();

    public static void checkKeysPressed(int key) {
        if (key == ModKeyBindings.SWITCH_ARMARIUM_SLOT.getKey().getValue()) {
            Player player = MINECRAFT.player;
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
