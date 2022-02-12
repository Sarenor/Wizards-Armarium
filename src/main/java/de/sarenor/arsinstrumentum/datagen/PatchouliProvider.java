package de.sarenor.arsinstrumentum.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.ApparatusPage;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.IPatchouliPage;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.PatchouliBuilder;
import com.hollingsworth.arsnouveau.common.datagen.patchouli.TextPage;
import de.sarenor.arsinstrumentum.setup.Registration;
import lombok.extern.log4j.Log4j2;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.hollingsworth.arsnouveau.common.datagen.PatchouliProvider.EQUIPMENT;

@Log4j2
public class PatchouliProvider implements DataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private final DataGenerator generator;
    public List<PatchouliPage> pages = new ArrayList<>();

    public PatchouliProvider(DataGenerator generatorIn) {
        this.generator = generatorIn;
    }

    public void addEntries() {
        addBasicItem(Registration.WIZARDS_ARMARIUM.get(), EQUIPMENT, new ApparatusPage(Registration.WIZARDS_ARMARIUM.get()));
    }

    public void addBasicItem(ItemLike item, ResourceLocation category, IPatchouliPage recipePage) {
        PatchouliBuilder builder = new PatchouliBuilder(category, item.asItem().getDescriptionId())
                .withIcon(item.asItem())
                .withPage(new TextPage("ars_instrumentum.page." + item.asItem().getRegistryName().getPath()))
                .withPage(recipePage);
        this.pages.add(new PatchouliPage(builder, getPath(category, item.asItem().getRegistryName())));
    }

    public Path getPath(ResourceLocation category, ResourceLocation fileName) {
        return this.generator.getOutputFolder().resolve("data/ars_instrumentum/patchouli_books/wizards_scratchpad/en_us/entries/categories/" + category.getPath() + "/" + fileName.getPath() + ".json");
    }

    @Override
    public void run(HashCache cache) throws IOException {
        log.info("ArsInstrumentum: Patchouli-Generation started");
        addEntries();
        for (PatchouliPage patchouliPage : pages) {
            DataProvider.save(GSON, cache, patchouliPage.builder.build(), patchouliPage.path);
        }
        log.info("ArsInstrumentum: Patchouli-Generation ended");
    }

    @Override
    public String getName() {
        return "Patchouli";
    }

    public static record PatchouliPage(PatchouliBuilder builder, Path path) {
    }
}
