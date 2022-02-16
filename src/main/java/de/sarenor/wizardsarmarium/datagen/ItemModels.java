package de.sarenor.wizardsarmarium.datagen;

import com.google.common.base.Preconditions;
import de.sarenor.wizardsarmarium.WizardsArmarium;
import de.sarenor.wizardsarmarium.setup.Registration;
import lombok.extern.log4j.Log4j2;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

@Log4j2
public class ItemModels extends ItemModelProvider {

    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, WizardsArmarium.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        log.info("Wizards Armarium: RegisterItemModels started");
        for (RegistryObject<Item> item : Registration.ITEMS.getEntries()) {
            try {
                getBuilder(item.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", itemTexture(item.get()));
            } catch (Exception e) {
                System.out.println("No texture for " + item.toString());
            }
        }
        log.info("Wizards Armarium: RegisterItemModels ended");
    }

    private ResourceLocation itemTexture(final Item item) {
        final ResourceLocation name = registryName(item);
        return new ResourceLocation(name.getNamespace(), "items" + "/" + name.getPath());
    }

    private ResourceLocation registryName(final Item item) {
        return Preconditions.checkNotNull(item.getRegistryName(), "Item %s has a null registry name", item);
    }
}
