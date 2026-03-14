package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;

public class EmeraldRecipeGenerator extends FabricRecipeProvider {

    public EmeraldRecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {
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
    public String getName() {
        return "";
    }
}
