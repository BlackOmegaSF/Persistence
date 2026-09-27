package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class EmeraldRecipeGenerator extends FabricRecipeProvider {

    public EmeraldRecipeGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes, BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, Items.EMERALD)
                        .pattern("iii")
                        .pattern("iei")
                        .pattern("iii")
                        .define('i', Items.IRON_INGOT)
                        .define('e', Items.EMERALD)
                        .unlockedBy("iron", has(Items.IRON_INGOT))
                        .unlockedBy("emerald", has(Items.EMERALD))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "EmeraldRecipeGenerator";
    }
}
