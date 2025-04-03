package com.kleinercode.fabric.persistence;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class Persistence implements DedicatedServerModInitializer {

    public static final String MOD_ID = "persistence";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeServer() {

        // Check if keepInventory is enabled, and notify if it is
        ServerLifecycleEvents.SERVER_STARTED.register((server) -> {

            Text keepInventoryMessageBase = Text.literal("Make sure to turn keepInventory off in world ");
            for (ServerWorld world : server.getWorlds()) {
                if (world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
                    MutableText text = keepInventoryMessageBase.copy();
                    text.append(world.asString());
                    world.getServer().getPlayerManager().broadcast(text, false);
                }
            }

        });

        PlayerDeathDropItemsCallback.EVENT.register((mainInventory, equipment, player) -> {
            // Handle main inventory
            for (int i = 0; i < mainInventory.size(); ++i) {
                ItemStack itemStack = mainInventory.get(i);
                if (!itemStack.isEmpty()) {
                    if (itemStack.isIn(ConventionalItemTags.SHULKER_BOXES)) {
                        // Further shulker processing
                        List<ItemStack> shulkerInventory = Utils.getShulkerInventory(itemStack);
                        for (int j = 0; j < shulkerInventory.size(); ++j) {
                            ItemStack slotItem = shulkerInventory.get(j);
                            if (!Utils.checkForPersistence(slotItem)) {
                                // drop the item and remove it from inventory
                                player.dropItem(slotItem, true, false);
                                shulkerInventory.set(j, ItemStack.EMPTY);
                            }
                        }
                        itemStack.set(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(shulkerInventory));
                    } else {
                        // Check for persistence
                        if (!Utils.checkForPersistence(itemStack)) {
                            // drop the item and remove it from inventory
                            player.dropItem(itemStack, true, false);
                            mainInventory.set(i, ItemStack.EMPTY);
                        }
                    }
                }
            }

            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack equipmentStack = equipment.get(slot);
                if (!equipmentStack.isEmpty()) {
                    if (equipmentStack.isIn(ConventionalItemTags.SHULKER_BOXES)) {
                        // Process shulker box
                        List<ItemStack> equippedShulkerInventory = Utils.getShulkerInventory(equipmentStack);
                        for (int k = 0; k < equippedShulkerInventory.size(); ++k) {
                            ItemStack slotItem = equippedShulkerInventory.get(k);
                            if (!Utils.checkForPersistence(slotItem)) {
                                // drop the item and remove it from inventory
                                player.dropItem(slotItem, true, false);
                                equippedShulkerInventory.set(k, ItemStack.EMPTY);
                            }
                        }
                        equipmentStack.set(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(equippedShulkerInventory));
                    } else {
                        // Check for persistence
                        if (!Utils.checkForPersistence(equipmentStack)) {
                            // drop the item and remove it from inventory
                            player.dropItem(equipmentStack, true, false);
                            equipment.put(slot, ItemStack.EMPTY);
                        }
                    }
                }
            }

            return ActionResult.FAIL;
        });


    }

    //TODO Add command to make something persistent
    //TODO Add logic for crafting reinforced emerald

}
