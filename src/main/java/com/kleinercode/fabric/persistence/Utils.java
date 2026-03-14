package com.kleinercode.fabric.persistence;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.component.ItemLore;

public class Utils {

    public static boolean checkForPersistence(ItemStack itemStack) {
        if (itemStack.isEmpty()) return false;
        ItemLore lore = itemStack.get(DataComponents.LORE);
        if (lore == null) return false;
        for (Component text : lore.lines()) {
            if (text.contains(Component.literal("Persistent"))) return true;
        }
        for (Component text : lore.styledLines()) {
            if (text.contains(Component.literal("Persistent"))) return true;
            for (Component textSibling : text.getSiblings()) {
                if (textSibling.contains(Component.literal("Persistent"))) return true;
            }
        }
        return false;
    }

    public static NonNullList<ItemStack> getShulkerInventory(ItemStack shulkerBox) {
        ItemContainerContents container = shulkerBox.get(DataComponents.CONTAINER);
        List<ItemStack> tempSlots = new ArrayList<>(container.stream().toList());
        NonNullList<ItemStack> slots = NonNullList.withSize(27, ItemStack.EMPTY);
        for (int i = 0; i < tempSlots.size(); i++) {
            slots.set(i, tempSlots.get(i));
        }
        return slots;
    }

    public static void addPersistence(ItemStack stack) {
        // Adds persistence to an ItemStack, except if it's empty or already has persistence
        if (stack.isEmpty()) return;
        if (checkForPersistence(stack)) return;
        ItemLore lore = stack.get(DataComponents.LORE);
        List<Component> newLines = new ArrayList<>();
        if (lore != null) {
            newLines.addAll(lore.lines());
        }
        newLines.add(Component.literal("Persistent"));
        stack.set(DataComponents.LORE, new ItemLore(newLines));
    }

}
