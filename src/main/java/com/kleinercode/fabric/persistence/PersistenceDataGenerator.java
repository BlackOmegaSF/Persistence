package com.kleinercode.fabric.persistence;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.*;

import java.util.concurrent.CompletableFuture;

public class PersistenceDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.createPack().addProvider(EmeraldRecipeGenerator::new);
    }

    private static class EmeraldRecipeGenerator extends FabricRecipeProvider {

        public EmeraldRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
            return new RecipeGenerator(wrapperLookup, recipeExporter) {
                @Override
                public void generate() {
                    createShaped(RecipeCategory.MISC, Items.EMERALD)
                            .pattern("iii")
                            .pattern("iei")
                            .pattern("iii")
                            .input('i', Items.IRON_INGOT)
                            .input('e', Items.EMERALD)
                            .criterion("iron", conditionsFromItem(Items.IRON_INGOT))
                            .criterion("emerald", conditionsFromItem(Items.EMERALD))
                            .offerTo(exporter);
                }
            };
        }

        @Override
        public String getName() {
            return "";
        }
    }



}
