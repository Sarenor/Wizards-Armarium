package de.sarenor.arsinstrumentum.setup;

import com.hollingsworth.arsnouveau.ArsNouveau;
import de.sarenor.arsinstrumentum.ArsInstrumentum;
import de.sarenor.arsinstrumentum.items.curios.WizardsArmarium;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArsInstrumentum.MODID);
    private static final Item.Properties DEFAULT_ITEM_PROPERTIES = (new Item.Properties()).tab(ArsNouveau.itemGroup);
    public static final RegistryObject<Item> WIZARDS_ARMARIUM = ITEMS.register(WizardsArmarium.WIZARDS_ARMARIUM_ID, () -> new Item(DEFAULT_ITEM_PROPERTIES));

    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
