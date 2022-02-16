package de.sarenor.wizardsarmarium.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SerializationUtiils {

    private static final int COMPOUND_TAG_TYPE = 10;
    private static final String X = "x";
    private static final String Y = "y";
    private static final String Z = "z";

    public static ListTag serializeItemList(List<ItemStack> items) {
        ListTag itemList = new ListTag();
        items.forEach((itemstack) -> itemList.add(itemstack.serializeNBT()));
        return itemList;
    }

    public static List<ItemStack> deserializeItemList(CompoundTag compoundTag, String tag) {
        List<ItemStack> itemStacks = new ArrayList<>();
        if (compoundTag.contains(tag)) {
            ListTag itemList = compoundTag.getList(tag, COMPOUND_TAG_TYPE);
            for (int i = 0; i < itemList.size(); i++) {
                itemStacks.add(ItemStack.of(itemList.getCompound(i)));
            }
            return itemStacks;
        }
        return itemStacks;
    }
}
