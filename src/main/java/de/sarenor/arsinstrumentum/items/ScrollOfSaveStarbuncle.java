package de.sarenor.arsinstrumentum.items;

import com.hollingsworth.arsnouveau.ArsNouveau;
import com.hollingsworth.arsnouveau.common.entity.Starbuncle;
import com.hollingsworth.arsnouveau.common.items.ModItem;
import com.hollingsworth.arsnouveau.common.util.PortUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import static com.hollingsworth.arsnouveau.common.entity.Starbuncle.FROM_POS;
import static com.hollingsworth.arsnouveau.common.entity.Starbuncle.TO_POS;

public class ScrollOfSaveStarbuncle extends ModItem {

    public static final String SCROLL_OF_SAVE_STARBUNCLE_ID = "scroll_of_save_starbuncle";
    public static final String SAVED_CONFIGURATION = "Saved Starbuncle configuration";
    public static final String APPLIED_CONFIGURATION = "Applied Starbuncle configuration";
    public static final String CLEARED_CONFIGURATION = "Cleared Starbuncle configuration";

    private static final String X = "x";
    private static final String Y = "y";
    private static final String Z = "z";
    private static final String FROM_LIST = "from_list";
    private static final String TO_LIST = "to_list";
    private static final String TOOLTIP = "tooltip";
    private static final String SCROLL_OF_SAVE_TAG_ID = "scroll_of_save_starbuncle_tag";

    public ScrollOfSaveStarbuncle() {
        super((new Properties()).stacksTo(1).tab(ArsNouveau.itemGroup));
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack doNotUseStack, Player playerEntity, LivingEntity target, InteractionHand hand) {
        if (playerEntity.level.isClientSide || hand != InteractionHand.MAIN_HAND) {
            return InteractionResult.PASS;
        }

        ItemStack heldScroll = playerEntity.getItemInHand(hand);
        if (target instanceof Starbuncle starbuncle) {
            if (playerEntity.isShiftKeyDown()) {
                apply(heldScroll, starbuncle, playerEntity);
            } else {
                store(heldScroll, starbuncle, playerEntity);
            }
        }

        return InteractionResult.SUCCESS;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player playerEntity, InteractionHand hand) {
        if (playerEntity.isShiftKeyDown() && hand == InteractionHand.MAIN_HAND) {
            clear(playerEntity.getItemInHand(hand), playerEntity);
        }
        return InteractionResultHolder.pass(playerEntity.getItemInHand(hand));
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader world, BlockPos pos, Player player) {
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag p_77624_4_) {
        CompoundTag scrollTag = stack.getOrCreateTag();
        if (scrollTag.contains(SCROLL_OF_SAVE_TAG_ID)) {
            tooltip.add(new TextComponent(scrollTag.getCompound(SCROLL_OF_SAVE_TAG_ID).getString(TOOLTIP)));
        }
    }

    private void store(ItemStack scroll, Starbuncle starbuncle, Player playerEntity) {
        CompoundTag scrollTag = scroll.getOrCreateTag();
        CompoundTag configTag = new CompoundTag();
        configTag.put(FROM_LIST, serializeBlockPosList(starbuncle.FROM_LIST));
        configTag.put(TO_LIST, serializeBlockPosList(starbuncle.TO_LIST));
        configTag.putString(TOOLTIP, "Stored Config with " + starbuncle.FROM_LIST.size() + " Take-Locations and "
                + starbuncle.TO_LIST.size() + " Deposit-Locations");
        scrollTag.put(SCROLL_OF_SAVE_TAG_ID, configTag);
        scroll.setTag(scrollTag);
        PortUtil.sendMessage(playerEntity, new TextComponent(SAVED_CONFIGURATION));
    }

    private void apply(ItemStack scroll, Starbuncle starbuncle, Player playerEntity) {
        CompoundTag scrollTag = scroll.getOrCreateTag();
        if (scrollTag.contains(SCROLL_OF_SAVE_TAG_ID)) {
            CompoundTag configTag = scrollTag.getCompound(SCROLL_OF_SAVE_TAG_ID);
            starbuncle.FROM_LIST = deserializeBlockPosList(configTag.getList(FROM_LIST, 10));
            starbuncle.TO_LIST = deserializeBlockPosList(configTag.getList(TO_LIST, 10));
            starbuncle.getEntityData().set(FROM_POS, starbuncle.FROM_LIST.size());
            starbuncle.getEntityData().set(TO_POS, starbuncle.TO_LIST.size());
            PortUtil.sendMessage(playerEntity, new TextComponent(APPLIED_CONFIGURATION));
        }
    }

    private void clear(ItemStack scroll, Player playerEntity) {
        CompoundTag scrollTag = scroll.getOrCreateTag();
        scrollTag.remove(SCROLL_OF_SAVE_TAG_ID);
        scroll.setTag(scrollTag);
        PortUtil.sendMessage(playerEntity, new TextComponent(CLEARED_CONFIGURATION));
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
