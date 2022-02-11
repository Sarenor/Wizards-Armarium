package de.sarenor.arsinstrumentum.network;

import de.sarenor.arsinstrumentum.api.armarium.ArmariumSlot;
import de.sarenor.arsinstrumentum.api.armarium.ArmariumStorage;
import lombok.NoArgsConstructor;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static de.sarenor.arsinstrumentum.setup.Registration.WIZARDS_ARMARIUM;
import static de.sarenor.arsinstrumentum.utils.IterableUtils.iterableToList;

@NoArgsConstructor
public class WizardsArmariumSwitchMessage {

    private static final int HOTBAR_SIZE = 9;
    private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

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
                ArmariumStorage armariumStorage = new ArmariumStorage(
                        CuriosApi.getCuriosHelper().findEquippedCurio(WIZARDS_ARMARIUM.get(), player).get().getRight());
                ArmariumSlot armariumSlot = armariumStorage.storeAndGet(iterableToList(player.getArmorSlots()),
                        player.getInventory().items.subList(0, 9));

                setArmor(player, armariumSlot.getArmor());
                setHotbar(player, armariumSlot.getHotbar());
            }
        });
        ctx.get().setPacketHandled(true);
    }

    private void setArmor(Player player, List<ItemStack> armorItems) {
        for (EquipmentSlot equipmentSlot : ARMOR_SLOTS) {
            Optional<ItemStack> armorItem = armorItems.stream()
                    .filter(itemStack -> LivingEntity.getEquipmentSlotForItem(itemStack).equals(equipmentSlot))
                    .findFirst();
            player.setItemSlot(equipmentSlot, armorItem.orElse(ItemStack.EMPTY));
        }
    }

    private void setHotbar(Player player, List<ItemStack> hotbarItems) {
        Inventory inventory = player.getInventory();
        for (int i = 0; i < HOTBAR_SIZE; i++) {
            if (i < hotbarItems.size()) {
                inventory.setItem(i, hotbarItems.get(i));
            } else {
                inventory.setItem(i, ItemStack.EMPTY);
            }
        }
    }
}

