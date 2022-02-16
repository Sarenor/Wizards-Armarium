package de.sarenor.wizardsarmarium.items.curios.armarium;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmariumStorage {

    public static final String ARMARIUM_STORAGE_TAG_ID = WizardsArmarium.MODID + "_armarium_storage_tag";
    private static final String CURRENT_SLOT = "current_slot";
    private static final String FLAVORTEXT = "flavortext";

    private final Map<Slots, ArmariumSlot> armariumSlots = new HashMap<>();
    private Slots currentSlot;
    private String flavorText = "";
    private ItemStack armarium;

    public ArmariumStorage(ItemStack armarium) {
        this(armarium.getOrCreateTag());
        this.armarium = armarium;
    }

    private ArmariumStorage(CompoundTag itemTag) {
        CompoundTag tag = itemTag.getCompound(ARMARIUM_STORAGE_TAG_ID);

        this.currentSlot = tag.contains(CURRENT_SLOT) ? Slots.valueOf(tag.getString(CURRENT_SLOT)) : Slots.SLOT_ONE;
        this.flavorText = tag.getString(FLAVORTEXT);
        for (Slots slot : Slots.values()) {
            if (tag.contains(slot.name())) {
                armariumSlots.put(slot, ArmariumSlot.deserialize(tag.getCompound(slot.name())));
            }
        }
    }

    public ArmariumSlot storeAndGet(List<ItemStack> armorItems, List<ItemStack> hotbarItems) {
        ArmariumSlot armariumSlot = armariumSlots.getOrDefault(currentSlot, new ArmariumSlot());
        armariumSlot.setArmor(armorItems);
        armariumSlot.setHotbar(hotbarItems);
        armariumSlots.put(currentSlot, armariumSlot);

        currentSlot = Slots.getNextSlot(currentSlot);

        writeArmariumStorageToArmariumItem();
        return armariumSlots.getOrDefault(currentSlot, new ArmariumSlot());
    }

    public String getTooltip() {
        if (armariumSlots != null && !armariumSlots.isEmpty()) {
            return "Next slots armor is: " + armariumSlots.get(Slots.getNextSlot(currentSlot)).listArmor();
        } else {
            return "";
        }
    }

    private void writeArmariumStorageToArmariumItem() {
        if (armarium == null || armarium.isEmpty()) {
            return;
        }
        CompoundTag tag = armarium.getOrCreateTag();
        tag.put(ARMARIUM_STORAGE_TAG_ID, writeTag()); // Nest our tags so we dont cause conflicts
        armarium.setTag(tag);
    }

    private CompoundTag writeTag() {
        CompoundTag armariumTag = new CompoundTag();
        armariumTag.putString(CURRENT_SLOT, currentSlot.name());
        armariumTag.putString(FLAVORTEXT, flavorText);


        for (Slots slot : Slots.values()) {
            armariumTag.put(slot.name(), armariumSlots.getOrDefault(slot, new ArmariumSlot()).serialize());
        }
        return armariumTag;
    }
}
