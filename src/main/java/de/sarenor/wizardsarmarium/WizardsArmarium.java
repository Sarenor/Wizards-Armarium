package de.sarenor.wizardsarmarium;

import de.sarenor.wizardsarmarium.network.Networking;
import de.sarenor.wizardsarmarium.setup.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WizardsArmarium.MODID)
public class WizardsArmarium {
    public static final String MODID = "wizards_armarium";
    public static ForgeConfigSpec SERVER_CONFIG;

    public static CreativeModeTab itemGroup = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), MODID) {
        @Override
        public ItemStack makeIcon() {
            return Registration.WIZARDS_ARMARIUM.get().getDefaultInstance();
        }
    };

    public WizardsArmarium() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Registration.init(bus);
        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        bus.addListener(this::sendImc);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void sendImc(InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().build());
    }

    private void setup(final FMLCommonSetupEvent event) {
        Networking.registerMessages();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }
}
