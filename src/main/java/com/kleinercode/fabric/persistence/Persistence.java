package com.kleinercode.fabric.persistence;

import com.kleinercode.fabric.persistence.utils.ReinforcedEmeraldProvider;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.gamerules.GameRules;
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

            Component keepInventoryMessageBase = Component.literal("Make sure to turn keepInventory off in world ");
            for (ServerLevel world : server.getAllLevels()) {
                if (world.getGameRules().get(GameRules.KEEP_INVENTORY)) {
                    MutableComponent text = keepInventoryMessageBase.copy();
                    text.append(world.gatherChunkSourceStats());
                    world.getServer().getPlayerList().broadcastSystemMessage(text, false);
                    LOGGER.warn(text.toString());
                }
            }

        });

        PlayerDeathDropItemsCallback.EVENT.register((mainInventory, equipment, player) -> {
            // Handle main inventory
            for (int i = 0; i < mainInventory.size(); ++i) {
                ItemStack itemStack = mainInventory.get(i);
                if (!itemStack.isEmpty()) {
                    if (itemStack.is(ConventionalItemTags.SHULKER_BOXES)) {
                        // Further shulker processing
                        List<ItemStack> shulkerInventory = Utils.getShulkerInventory(itemStack);
                        for (int j = 0; j < shulkerInventory.size(); ++j) {
                            ItemStack slotItem = shulkerInventory.get(j);
                            if (!Utils.checkForPersistence(slotItem)) {
                                // drop the item and remove it from inventory
                                player.drop(slotItem, true, false);
                                shulkerInventory.set(j, ItemStack.EMPTY);
                            }
                        }
                        itemStack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(shulkerInventory));
                    } else {
                        // Check for persistence
                        if (!Utils.checkForPersistence(itemStack)) {
                            // drop the item and remove it from inventory
                            player.drop(itemStack, true, false);
                            mainInventory.set(i, ItemStack.EMPTY);
                        }
                    }
                }
            }

            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack equipmentStack = equipment.get(slot);
                if (!equipmentStack.isEmpty()) {
                    if (equipmentStack.is(ConventionalItemTags.SHULKER_BOXES)) {
                        // Process shulker box
                        List<ItemStack> equippedShulkerInventory = Utils.getShulkerInventory(equipmentStack);
                        for (int k = 0; k < equippedShulkerInventory.size(); ++k) {
                            ItemStack slotItem = equippedShulkerInventory.get(k);
                            if (!Utils.checkForPersistence(slotItem)) {
                                // drop the item and remove it from inventory
                                player.drop(slotItem, true, false);
                                equippedShulkerInventory.set(k, ItemStack.EMPTY);
                            }
                        }
                        equipmentStack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(equippedShulkerInventory));
                    } else {
                        // Check for persistence
                        if (!Utils.checkForPersistence(equipmentStack)) {
                            // drop the item and remove it from inventory
                            player.drop(equipmentStack, true, false);
                            equipment.set(slot, ItemStack.EMPTY);
                        }
                    }
                }
            }

            return InteractionResult.FAIL;
        });

        // Add persistence to shulker boxes
        CraftItemCallback.EVENT.register((craftingRecipeInput, resultStack) -> {

            if (resultStack.itemStack.is(ConventionalItemTags.SHULKER_BOXES)) {
                Utils.addPersistence(resultStack.itemStack);
                return InteractionResult.FAIL;
            } else {
                return InteractionResult.PASS;
            }

        });

        // Check for Reinforced Emerald crafting
        CraftItemCallback.EVENT.register((craftingRecipeInput, resultStack) -> {

            if (resultStack.itemStack.is(Items.EMERALD)) {
                // Manually check for valid reinforced emerald input
                if (craftingRecipeInput.height() != 3) return InteractionResult.PASS;
                if (craftingRecipeInput.width() != 3) return InteractionResult.PASS;
                List<ItemStack> stacks = craftingRecipeInput.items();
                if (stacks.size() < 9) return InteractionResult.PASS;
                if (!stacks.get(0).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(1).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(2).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(3).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(4).is(Items.EMERALD)) return InteractionResult.PASS;
                if (!stacks.get(5).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(6).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(7).is(Items.IRON_INGOT)) return InteractionResult.PASS;
                if (!stacks.get(8).is(Items.IRON_INGOT)) return InteractionResult.PASS;

                // It's a reinforced emerald, make it so
                resultStack.setItemStack(ReinforcedEmeraldProvider.createReinforcedEmerald());
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;

        });

        // Check for Shulker Box crafting

        CraftItemCallback.EVENT.register(((craftingRecipeInput, resultStack) -> {

            if (resultStack.itemStack.is(ItemTags.SHULKER_BOXES)) {
                if (!Utils.checkForPersistence(resultStack.itemStack)) {
                    // Result is a shulker box without Persistence, so give it Persistence
                    Utils.addPersistence(resultStack.itemStack);
                }
            }

            // Return pass because we altered the item stack but don't need to cancel the whole event
            return InteractionResult.PASS;

        }));

    }

}
