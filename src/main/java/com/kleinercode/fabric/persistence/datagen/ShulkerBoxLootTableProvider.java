package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;

public class ShulkerBoxLootTableProvider extends FabricBlockLootSubProvider {

    public ShulkerBoxLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate() {



        for (Block dyed_shulker_box : Blocks.DYED_SHULKER_BOX.asList()) {

            add(
                dyed_shulker_box,
                LootTable.lootTable().withPool(
                    applyExplosionCondition(
                        dyed_shulker_box,
                        LootPool.lootPool()
                            .setRolls(ContextIntProviders.exactly(1))
                            .add(LootItem.lootTableItem(dyed_shulker_box)
                                .apply(
                                    CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                                        .include(DataComponents.CUSTOM_NAME)
                                        .include(DataComponents.CONTAINER)
                                        .include(DataComponents.LOCK)
                                        .include(DataComponents.CONTAINER_LOOT)
                                        .include(DataComponents.LORE)
                                )
                            )
                    )
                )
            );

        }

        add(
            Blocks.SHULKER_BOX,
            LootTable.lootTable().withPool(
                applyExplosionCondition(
                    Blocks.SHULKER_BOX,
                    LootPool.lootPool()
                        .setRolls(ContextIntProviders.exactly(1))
                        .add(LootItem.lootTableItem(Blocks.SHULKER_BOX)
                            .apply(
                                CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                                    .include(DataComponents.CUSTOM_NAME)
                                    .include(DataComponents.CONTAINER)
                                    .include(DataComponents.LOCK)
                                    .include(DataComponents.CONTAINER_LOOT)
                                    .include(DataComponents.LORE)
                            )
                        )
                )
            )
            );

    }
}
