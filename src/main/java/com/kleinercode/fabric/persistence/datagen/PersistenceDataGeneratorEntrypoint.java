package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PersistenceDataGeneratorEntrypoint implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        generator.createPack().addProvider(EmeraldRecipeGenerator::new);
    }



}
