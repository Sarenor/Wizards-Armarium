package de.sarenor.arsinstrumentum.datagen;

import com.google.common.base.Preconditions;
import de.sarenor.arsinstrumentum.ArsInstrumentum;
import de.sarenor.arsinstrumentum.setup.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ArsInstrumentumItemModels extends ItemModelProvider {

    public ArsInstrumentumItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ArsInstrumentum.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> item : ItemRegistry.ITEMS.getEntries()) {
            try {
                getBuilder(item.get().getRegistryName().getPath()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", itemTexture(item.get()));
            } catch (Exception e) {
                System.out.println("No texture for " + item.toString());
            }
        }
    }

    private ResourceLocation itemTexture(final Item item) {
        final ResourceLocation name = registryName(item);
        return new ResourceLocation(name.getNamespace(), "items" + "/" + name.getPath());
    }

    private ResourceLocation registryName(final Item item) {
        return Preconditions.checkNotNull(item.getRegistryName(), "Item %s has a null registry name", item);
    }
}
