package de.sarenor.arsinstrumentum.setup;

import com.hollingsworth.arsnouveau.ArsNouveau;
import de.sarenor.arsinstrumentum.ArsInstrumentum;
import de.sarenor.arsinstrumentum.items.curios.WizardsVestium;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ArsInstrumentum.MODID);
    private static final Item.Properties DEFAULT_ITEM_PROPERTIES = (new Item.Properties()).tab(ArsNouveau.itemGroup);
    public static final RegistryObject<Item> WIZARDS_VESTIUM = ITEMS.register(WizardsVestium.WIZARDS_VESTIUM_ID, () -> new Item(DEFAULT_ITEM_PROPERTIES));
}
