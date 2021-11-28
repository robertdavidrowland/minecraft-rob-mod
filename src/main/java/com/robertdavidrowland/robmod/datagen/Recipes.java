package com.robertdavidrowland.robmod.datagen;

import com.robertdavidrowland.robmod.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Registration.AMETHYST_AXE.get())
                .pattern(" xx")
                .pattern(" sx")
                .pattern(" s ")
                .define('x', Items.AMETHYST_SHARD)
                .define('s', Items.STICK)
                .group("robmod")
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.AMETHYST_PICKAXE.get())
                .pattern("xxx")
                .pattern(" s ")
                .pattern(" s ")
                .define('x', Items.AMETHYST_SHARD)
                .define('s', Items.STICK)
                .group("robmod")
                .unlockedBy("sticks", InventoryChangeTrigger.TriggerInstance.hasItems(Items.STICK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Registration.CHARGED_PICKAXE.get())
                .pattern(" c ")
                .pattern("ctc")
                .pattern(" c ")
                .define('t', Registration.AMETHYST_PICKAXE.get())
                .define('c', Items.COPPER_INGOT)
                .group("robmod")
                .unlockedBy("copper_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .unlockedBy("amethyst_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.AMETHYST_PICKAXE.get()))
                .save(consumer);
    }
}
