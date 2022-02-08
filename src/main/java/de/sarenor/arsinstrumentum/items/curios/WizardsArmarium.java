package de.sarenor.arsinstrumentum.items.curios;

import com.hollingsworth.arsnouveau.api.item.ArsNouveauCurio;
import net.minecraft.world.entity.LivingEntity;

public class WizardsArmarium extends ArsNouveauCurio {
    public static final String WIZARDS_ARMARIUM_ID = "wizards_armarium";

    public WizardsArmarium() {
        super(WIZARDS_ARMARIUM_ID);
    }

    public void wearableTick(LivingEntity player) {
    }
}