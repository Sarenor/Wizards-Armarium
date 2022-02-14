package de.sarenor.arsinstrumentum.utils;

import net.minecraft.core.BlockPos;

public class BlockPosUtils {
    public static String toString(BlockPos blockPos) {
        return "{X: " + blockPos.getX() + ", Y: " + blockPos.getY() + ", Z: " + blockPos.getZ() + "}";
    }
}
