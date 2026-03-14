package com.kleinercode.fabric.persistence.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import java.util.concurrent.CompletableFuture;

public class ShulkerBoxLootTableProvider extends FabricBlockLootTableProvider {

    public ShulkerBoxLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate() {

        add(
                Blocks.SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                            Items.SHULKER_BOX, LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1.0F))
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

        add(
                Blocks.BLACK_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.BLACK_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.BLACK_SHULKER_BOX)
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

        add(
                Blocks.BLUE_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.BLUE_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.BLUE_SHULKER_BOX)
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

        add(
                Blocks.BROWN_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.BROWN_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.BROWN_SHULKER_BOX)
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

        add(
                Blocks.CYAN_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.CYAN_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.CYAN_SHULKER_BOX)
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

        add(
                Blocks.GRAY_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.GRAY_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.GRAY_SHULKER_BOX)
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

        add(
                Blocks.GREEN_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.GREEN_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.GREEN_SHULKER_BOX)
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

        add(
                Blocks.LIGHT_BLUE_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.LIGHT_BLUE_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.LIGHT_BLUE_SHULKER_BOX)
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

        add(
                Blocks.LIGHT_GRAY_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.LIGHT_GRAY_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.LIGHT_GRAY_SHULKER_BOX)
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

        add(
                Blocks.LIME_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.LIME_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.LIME_SHULKER_BOX)
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

        add(
                Blocks.MAGENTA_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.MAGENTA_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.MAGENTA_SHULKER_BOX)
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

        add(
                Blocks.ORANGE_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.ORANGE_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.ORANGE_SHULKER_BOX)
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

        add(
                Blocks.PINK_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.PINK_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.PINK_SHULKER_BOX)
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

        add(
                Blocks.PURPLE_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.PURPLE_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.PURPLE_SHULKER_BOX)
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

        add(
                Blocks.RED_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.RED_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.RED_SHULKER_BOX)
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

        add(
                Blocks.WHITE_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.WHITE_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.WHITE_SHULKER_BOX)
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

        add(
                Blocks.YELLOW_SHULKER_BOX,
                LootTable.lootTable().withPool(
                        applyExplosionCondition(
                                Items.YELLOW_SHULKER_BOX, LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Blocks.YELLOW_SHULKER_BOX)
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
