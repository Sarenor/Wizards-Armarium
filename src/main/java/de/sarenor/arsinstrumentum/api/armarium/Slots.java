package de.sarenor.arsinstrumentum.api.armarium;

public enum Slots {
    SLOT_ONE, SLOT_TWO, SLOT_THREE;

    public static Slots getNextSlot(Slots slots) {
        return switch (slots) {
            case SLOT_ONE -> SLOT_TWO;
            case SLOT_TWO -> SLOT_THREE;
            case SLOT_THREE -> SLOT_ONE;
        };
    }
}
