package com.kleinercode.fabric.persistence;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static class ItemStackWrapper {
        public ItemStack itemStack;

        public ItemStackWrapper(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        public void setItemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
        }
    }

    public static boolean checkForPersistence(ItemStack itemStack) {
        if (itemStack.isEmpty()) return false;
        LoreComponent lore = itemStack.get(DataComponentTypes.LORE);
        if (lore == null) return false;
        for (Text text : lore.lines()) {
            if (text.contains(Text.literal("Persistent"))) return true;
        }
        for (Text text : lore.styledLines()) {
            if (text.contains(Text.literal("Persistent"))) return true;
            for (Text textSibling : text.getSiblings()) {
                if (textSibling.contains(Text.literal("Persistent"))) return true;
            }
        }
        return false;
    }

    public static DefaultedList<ItemStack> getShulkerInventory(ItemStack shulkerBox) {
        ContainerComponent container = shulkerBox.get(DataComponentTypes.CONTAINER);
        List<ItemStack> tempSlots = new ArrayList<>(container.stream().toList());
        DefaultedList<ItemStack> slots = DefaultedList.ofSize(27, ItemStack.EMPTY);
        for (int i = 0; i < tempSlots.size(); i++) {
            slots.set(i, tempSlots.get(i));
        }
        return slots;
    }

    public static void addPersistence(ItemStack stack) {
        // Adds persistence to an ItemStack, except if it's empty or already has persistence
        if (stack.isEmpty()) return;
        if (checkForPersistence(stack)) return;
        LoreComponent lore = stack.get(DataComponentTypes.LORE);
        List<Text> newLines = new ArrayList<>();
        if (lore != null) {
            newLines.addAll(lore.lines());
        }
        newLines.add(Text.literal("Persistent"));
        stack.set(DataComponentTypes.LORE, new LoreComponent(newLines));
    }

    public static final ItemStack REINFORCED_EMERALD = createReinforcedEmerald();

    private static ItemStack createReinforcedEmerald() {
        ItemStack emerald = new ItemStack(Items.EMERALD, 1);
        emerald.set(DataComponentTypes.LORE, new LoreComponent(List.of(Text.literal("Persistent"))));
        emerald.set(DataComponentTypes.ITEM_NAME, Text.literal("Reinforced Emerald"));
        return emerald;
    }

}
