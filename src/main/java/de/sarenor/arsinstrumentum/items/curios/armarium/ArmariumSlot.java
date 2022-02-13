package de.sarenor.arsinstrumentum.items.curios.armarium;

import de.sarenor.arsinstrumentum.ArsInstrumentum;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArmariumSlot {
    private static final String ARMARIUM_ARMOR_TAG = ArsInstrumentum.MODID + "_armarium_armor_tag";
    private static final String ARMARIUM_HOTBAR_TAG = ArsInstrumentum.MODID + "_armarium_hotbar_tag";

    private List<ItemStack> armor = new ArrayList<>();
    private List<ItemStack> hotbar = new ArrayList<>();

    public static ArmariumSlot deserialize(CompoundTag compoundTag) {
        ArmariumSlot armariumSlot = new ArmariumSlot();
        armariumSlot.setArmor(deserializeItemList(compoundTag, ARMARIUM_ARMOR_TAG));
        armariumSlot.setHotbar(deserializeItemList(compoundTag, ARMARIUM_HOTBAR_TAG));
        return armariumSlot;
    }

    private static List<ItemStack> deserializeItemList(CompoundTag compoundTag, String armariumArmorTag) {
        List<ItemStack> itemStacks = new ArrayList<>();
        if (compoundTag.contains(armariumArmorTag)) {
            ListTag itemList = compoundTag.getList(armariumArmorTag, 10);
            for (int i = 0; i < itemList.size(); i++) {
                itemStacks.add(ItemStack.of(itemList.getCompound(i)));
            }
            return itemStacks;
        }
        return itemStacks;
    }

    public CompoundTag serialize() {
        ListTag armorList = new ListTag();
        ListTag hotbarList = new ListTag();
        CompoundTag serialized = new CompoundTag();

        armor.forEach((itemstack) -> armorList.add(itemstack.serializeNBT()));
        hotbar.forEach((itemstack) -> hotbarList.add(itemstack.serializeNBT()));

        serialized.put(ARMARIUM_ARMOR_TAG, armorList);
        serialized.put(ARMARIUM_HOTBAR_TAG, hotbarList);
        return serialized;
    }

    public String listArmor() {
        return armor.stream()
                .map(ItemStack::getDisplayName)
                .map(Component::getString)
                .collect(Collectors.joining(", "));
    }
}
