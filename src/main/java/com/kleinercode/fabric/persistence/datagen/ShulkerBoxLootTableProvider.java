package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.CopyComponentsLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ShulkerBoxLootTableProvider extends FabricBlockLootTableProvider {

    public ShulkerBoxLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
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
                                    CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.BLACK_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.BLACK_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.BLACK_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.BLUE_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.BLUE_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.BLUE_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.BROWN_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.BROWN_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.BROWN_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.CYAN_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.CYAN_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.CYAN_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.GRAY_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.GRAY_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.GRAY_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.GREEN_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.GREEN_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.GREEN_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.LIGHT_BLUE_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.LIGHT_BLUE_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.LIGHT_BLUE_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.LIGHT_GRAY_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.LIGHT_GRAY_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.LIGHT_GRAY_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.LIME_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.LIME_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.LIME_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.MAGENTA_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.MAGENTA_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.MAGENTA_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.ORANGE_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.ORANGE_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.ORANGE_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.PINK_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.PINK_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.PINK_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.PURPLE_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.PURPLE_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.PURPLE_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.RED_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.RED_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.RED_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.WHITE_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.WHITE_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.WHITE_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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

        addDrop(
                Blocks.YELLOW_SHULKER_BOX,
                LootTable.builder().pool(
                        addSurvivesExplosionCondition(
                                Items.YELLOW_SHULKER_BOX, LootPool.builder()
                                        .rolls(ConstantLootNumberProvider.create(1.0F))
                                        .with(ItemEntry.builder(Blocks.YELLOW_SHULKER_BOX)
                                                .apply(
                                                        CopyComponentsLootFunction.blockEntity(LootContextParameters.BLOCK_ENTITY)
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
