package de.sarenor.wizardsarmarium.setup;

import de.sarenor.wizardsarmarium.WizardsArmarium;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Registration {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WizardsArmarium.MODID);

    public static final RegistryObject<Item> WIZARDS_ARMARIUM = ITEMS.register(de.sarenor.wizardsarmarium.items.curios.armarium.WizardsArmarium.WIZARDS_ARMARIUM_ID,
            () -> new de.sarenor.wizardsarmarium.items.curios.armarium.WizardsArmarium(new Item.Properties().stacksTo(1).tab(WizardsArmarium.itemGroup)));
    public static void init(IEventBus bus) {
        ITEMS.register(bus);
    }
}
