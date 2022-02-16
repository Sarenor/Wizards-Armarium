package de.sarenor.wizardsarmarium.items.curios.armarium;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.sarenor.wizardsarmarium.utils.SerializationUtiils.deserializeItemList;
import static de.sarenor.wizardsarmarium.utils.SerializationUtiils.serializeItemList;

@Getter
@Setter
public class ArmariumSlot {
    private static final String ARMARIUM_ARMOR_TAG = WizardsArmarium.MODID + "_armarium_armor_tag";
    private static final String ARMARIUM_HOTBAR_TAG = WizardsArmarium.MODID + "_armarium_hotbar_tag";

    private List<ItemStack> armor = new ArrayList<>();
    private List<ItemStack> hotbar = new ArrayList<>();

    public static ArmariumSlot deserialize(CompoundTag compoundTag) {
        ArmariumSlot armariumSlot = new ArmariumSlot();
        armariumSlot.setArmor(deserializeItemList(compoundTag, ARMARIUM_ARMOR_TAG));
        armariumSlot.setHotbar(deserializeItemList(compoundTag, ARMARIUM_HOTBAR_TAG));
        return armariumSlot;
    }

    public CompoundTag serialize() {
        CompoundTag serialized = new CompoundTag();
        serialized.put(ARMARIUM_ARMOR_TAG, serializeItemList(armor));
        serialized.put(ARMARIUM_HOTBAR_TAG, serializeItemList(hotbar));
        return serialized;
    }

    public String listArmor() {
        return armor.stream()
                .map(ItemStack::getDisplayName)
                .map(Component::getString)
                .collect(Collectors.joining(", "));
    }
}
