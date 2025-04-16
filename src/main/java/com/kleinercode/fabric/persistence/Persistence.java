package com.kleinercode.fabric.persistence;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
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

        // Add persistence to shulker boxes
        CraftItemCallback.EVENT.register((craftingRecipeInput, resultStack) -> {

            if (resultStack.itemStack.isIn(ConventionalItemTags.SHULKER_BOXES)) {
                Utils.addPersistence(resultStack.itemStack);
                return ActionResult.FAIL;
            } else {
                return ActionResult.PASS;
            }

        });

        // Check for Reinforced Emerald crafting
        CraftItemCallback.EVENT.register((craftingRecipeInput, resultStack) -> {

            if (resultStack.itemStack.isOf(Items.EMERALD)) {
                // Manually check for valid reinforced emerald input
                if (craftingRecipeInput.getHeight() != 3) return ActionResult.PASS;
                if (craftingRecipeInput.getWidth() != 3) return ActionResult.PASS;
                List<ItemStack> stacks = craftingRecipeInput.getStacks();
                if (stacks.size() < 9) return ActionResult.PASS;
                if (!stacks.get(0).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(1).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(2).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(3).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(4).isOf(Items.EMERALD)) return ActionResult.PASS;
                if (!stacks.get(5).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(6).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(7).isOf(Items.IRON_INGOT)) return ActionResult.PASS;
                if (!stacks.get(8).isOf(Items.IRON_INGOT)) return ActionResult.PASS;

                // It's a reinforced emerald, make it so
                resultStack.setItemStack(Utils.REINFORCED_EMERALD);
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;

        });

        // Check for Shulker Box crafting

        CraftItemCallback.EVENT.register(((craftingRecipeInput, resultStack) -> {

            if (resultStack.itemStack.isIn(ItemTags.SHULKER_BOXES)) {
                if (!Utils.checkForPersistence(resultStack.itemStack)) {
                    // Result is a shulker box without Persistence, so give it Persistence
                    Utils.addPersistence(resultStack.itemStack);
                }
            }

            // Return pass because we altered the item stack but don't need to cancel the whole event
            return ActionResult.PASS;

        }));

    }

}
