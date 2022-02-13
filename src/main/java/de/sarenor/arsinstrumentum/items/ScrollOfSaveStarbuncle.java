package de.sarenor.arsinstrumentum.items;

import com.hollingsworth.arsnouveau.common.entity.Starbuncle;
import com.hollingsworth.arsnouveau.common.items.ModItem;
import com.hollingsworth.arsnouveau.common.util.PortUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ScrollOfSaveStarbuncle extends ModItem {

    public static final String SCROLL_OF_SAVE_STARBUNCLE_ID = "scroll_of_save_starbuncle";
    public static final String SAVED_CONFIGURATION = "ars_instrumentum.scroll_of_save_starbuncle.saved";
    public static final String APPLIED_CONFIGURATION = "ars_instrumentum.scroll_of_save_starbuncle.applied";
    public static final String CLEARED_CONFIGURATION = "ars_instrumentum.scroll_of_save_starbuncle.cleared";

    private static final String X = "x";
    private static final String Y = "y";
    private static final String Z = "z";
    private static final String FROM_LIST = "from_list";
    private static final String TO_LIST = "to_list";
    private static final String TOOLTIP = "tooltip";
    private static final String SCROLL_OF_SAVE_TAG_ID = "scroll_of_save";

    public ScrollOfSaveStarbuncle(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack doNotUseStack, Player playerEntity, LivingEntity target, InteractionHand hand) {
        if (playerEntity.level.isClientSide || hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }

        ItemStack heldScroll = playerEntity.getItemInHand(hand);
        if (target instanceof Starbuncle starbuncle) {
            if (playerEntity.isShiftKeyDown()) {
                store(heldScroll, starbuncle);
                PortUtil.sendMessage(playerEntity, new TranslatableComponent(SAVED_CONFIGURATION));
            } else {
                apply(heldScroll, starbuncle);
                PortUtil.sendMessage(playerEntity, new TranslatableComponent(APPLIED_CONFIGURATION));
            }
        } else if (playerEntity.isShiftKeyDown()) {
            clear(heldScroll);
            PortUtil.sendMessage(playerEntity, new TranslatableComponent(CLEARED_CONFIGURATION));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader world, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag p_77624_4_) {
        CompoundTag scrollTag = stack.getOrCreateTag();
        if(scrollTag.contains(SCROLL_OF_SAVE_TAG_ID)) {
            tooltip.add(new TextComponent(scrollTag.getCompound(SCROLL_OF_SAVE_TAG_ID).getString(TOOLTIP)));
        }
    }

    private void store(ItemStack scroll, Starbuncle starbuncle) {
        CompoundTag scrollTag = scroll.getOrCreateTag();
        CompoundTag configTag = new CompoundTag();
        configTag.put(FROM_LIST, serializeBlockPosList(starbuncle.FROM_LIST));
        configTag.put(TO_LIST, serializeBlockPosList(starbuncle.TO_LIST));
        configTag.putString(TOOLTIP, "Stored Config with " + starbuncle.FROM_LIST.size() + " Take-Locations and "
                + starbuncle.TO_LIST + " Deposit-Locations");
        scrollTag.put(SCROLL_OF_SAVE_TAG_ID, configTag);
        scroll.setTag(scrollTag);
    }

    private void apply(ItemStack scroll, Starbuncle starbuncle) {
        CompoundTag scrollTag = scroll.getOrCreateTag();
        if (scrollTag.contains(SCROLL_OF_SAVE_TAG_ID)) {
            CompoundTag configTag = scrollTag.getCompound(SCROLL_OF_SAVE_TAG_ID);
            starbuncle.FROM_LIST = deserializeBlockPosList(configTag.getList(FROM_LIST, 10));
            starbuncle.TO_LIST = deserializeBlockPosList(configTag.getList(TO_LIST, 10));
        }
    }

    private void clear(ItemStack scroll) {
        CompoundTag scrollTag = scroll.getOrCreateTag();
        scrollTag.remove(SCROLL_OF_SAVE_TAG_ID);
        scroll.setTag(scrollTag);
    }

    private ListTag serializeBlockPosList(List<BlockPos> blockPositions) {
        ListTag serializedBlockPositions = new ListTag();
        blockPositions.forEach(blockPos -> serializedBlockPositions.add(serializeBlockPos(blockPos)));
        return serializedBlockPositions;
    }

    private CompoundTag serializeBlockPos(BlockPos blockPos) {
        CompoundTag serializedBlockPos = new CompoundTag();
        serializedBlockPos.putInt(X, blockPos.getX());
        serializedBlockPos.putInt(Y, blockPos.getY());
        serializedBlockPos.putInt(Z, blockPos.getZ());
        return serializedBlockPos;
    }

    private List<BlockPos> deserializeBlockPosList(ListTag serializedBlockPositions) {
        List<BlockPos> blockPositions = new ArrayList<>();
        for (int i = 0; i < serializedBlockPositions.size(); i++) {
            blockPositions.add(deserializeBlockPos(serializedBlockPositions.getCompound(i)));
        }
        return blockPositions;
    }

    private BlockPos deserializeBlockPos(CompoundTag serializedBlockPosition) {
        return new BlockPos(serializedBlockPosition.getInt(X), serializedBlockPosition.getInt(Y), serializedBlockPosition.getInt(Z));
    }
}
