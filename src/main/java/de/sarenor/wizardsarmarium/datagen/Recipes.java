package de.sarenor.wizardsarmarium.datagen;

import de.sarenor.wizardsarmarium.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(Registration.WIZARDS_ARMARIUM.get(), 1)
                .unlockedBy("has_blaze_rod", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BLAZE_ROD))
                .requires(Items.LEATHER)
                .requires(Ingredient.of(Tags.Items.GEMS_DIAMOND), 2)
                .requires(Items.BLAZE_ROD, 4)
                .requires(Items.ENDER_CHEST)
                .save(consumer);
    }
}
