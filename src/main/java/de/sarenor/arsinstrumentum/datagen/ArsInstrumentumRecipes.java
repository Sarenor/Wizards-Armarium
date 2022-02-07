package de.sarenor.arsinstrumentum.datagen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.EnchantingApparatusRecipe;
import com.hollingsworth.arsnouveau.api.enchanting_apparatus.IEnchantingRecipe;
import com.hollingsworth.arsnouveau.common.datagen.ApparatusRecipeBuilder;
import com.hollingsworth.arsnouveau.setup.ItemsRegistry;
import de.sarenor.arsinstrumentum.setup.ItemRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArsInstrumentumRecipes implements DataProvider {

    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();
    private static final Logger LOGGER = LogManager.getLogger();
    private final DataGenerator generator;
    List<EnchantingApparatusRecipe> recipes = new ArrayList<>();

    public ArsInstrumentumRecipes(DataGenerator generatorIn) {
        this.generator = generatorIn;
    }

    private static Path getRecipePath(Path pathIn, String str) {
        return pathIn.resolve("data/Apparatus/recipes/" + str + ".json");
    }

    @Override
    public void run(HashCache cache) throws IOException {
        LOGGER.info("ArsInstrumentum: Recipe-Generation started");
        addEntries();
        Path output = this.generator.getOutputFolder();
        for (IEnchantingRecipe g : recipes) {
            if (g instanceof EnchantingApparatusRecipe) {
                System.out.println(g);
                Path path = getRecipePath(output, ((EnchantingApparatusRecipe) g).getId().getPath());
                DataProvider.save(GSON, cache, ((EnchantingApparatusRecipe) g).asRecipe(), path);
            }
        }
        LOGGER.info("ArsInstrumentum: Recipe-Generation ended");
    }

    public void addEntries() {
        this.recipes.add(ApparatusRecipeBuilder.builder()
                .withResult(ItemRegistry.WIZARDS_VESTIUM.get())
                .withReagent(ItemsRegistry.MUNDANE_BELT)
                .withPedestalItem(4, ItemsRegistry.BLAZE_FIBER)
                .withPedestalItem(2, Items.DIAMOND)
                .withPedestalItem(Items.ENDER_CHEST)
                .withPedestalItem(ItemsRegistry.MANIPULATION_ESSENCE)
                .build());
    }

    @Override
    public String getName() {
        return "ArsInstrumntumApparatus";
    }
}
