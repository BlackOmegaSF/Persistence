package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyComponentsLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.context.ContextType;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ShulkerBoxLootTableProvider extends FabricBlockLootTableProvider {

    protected ShulkerBoxLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate() {

        addDrop(
                Blocks.SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                            Items.SHULKER_BOX, LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0F))
                            .with(ItemEntry.builder(Blocks.SHULKER_BOX)
                                .apply(
                                    CopyComponentsLootFunction.builder(CopyComponentsLootFunction.Source.BLOCK_ENTITY)
                                    .include(DataComponentTypes.CUSTOM_NAME)
                                    .include(DataComponentTypes.CONTAINER)
                                    .include(DataComponentTypes.LOCK)
                                    .include(DataComponentTypes.CONTAINER_LOOT)
                                    .include(DataComponentTypes.LORE)
                                )
                            )
                        )
                )
        );

    }
}
