package de.sarenor.wizardsarmarium.network;

import de.sarenor.wizardsarmarium.items.curios.armarium.WizardsArmarium;
import lombok.NoArgsConstructor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

import static de.sarenor.wizardsarmarium.setup.Registration.WIZARDS_ARMARIUM;

@NoArgsConstructor
public class WizardsArmariumSwitchMessage {
    //Decoder
    public WizardsArmariumSwitchMessage(FriendlyByteBuf buf) {
    }

    //Encoder
    public void toBytes(FriendlyByteBuf buf) {
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null && CuriosApi.getCuriosHelper().findEquippedCurio(WIZARDS_ARMARIUM.get(), player).isPresent()) {
                WizardsArmarium.handleArmariumSwitch(player);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

