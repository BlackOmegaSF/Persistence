package com.kleinercode.fabric.persistence;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean checkForPersistence(ItemStack itemStack) {
        if (itemStack.isEmpty()) return false;
        LoreComponent lore = itemStack.get(DataComponentTypes.LORE);
        if (lore == null) return false;
        for (Text text : lore.lines()) {
            if (!text.contains(Text.literal("Persistent"))) return true;
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

}
