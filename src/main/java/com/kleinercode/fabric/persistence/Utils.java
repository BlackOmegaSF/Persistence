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

    public static boolean checkForPersistence(ItemStack itemStack) {
        if (itemStack.isEmpty()) return false;
        LoreComponent lore = itemStack.get(DataComponentTypes.LORE);
        if (lore == null) return false;
        for (Text text : lore.lines()) {
            if (text.contains(Text.literal("Persistent"))) return true;
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

    public static final CraftingRecipeInput EMERALD_INPUT = CraftingRecipeInput.create(3, 3, List.of(
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.EMERALD),
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.IRON_INGOT),
            new ItemStack(Items.IRON_INGOT)
    ));

    public static final ItemStack REINFORCED_EMERALD = createReinforcedEmerald();

    private static ItemStack createReinforcedEmerald() {
        ItemStack emerald = new ItemStack(Items.EMERALD, 1);
        emerald.set(DataComponentTypes.LORE, new LoreComponent(List.of(Text.literal("Persistent"))));
        emerald.set(DataComponentTypes.ITEM_NAME, Text.literal("Reinforced Emerald"));
        return emerald;
    }

}
