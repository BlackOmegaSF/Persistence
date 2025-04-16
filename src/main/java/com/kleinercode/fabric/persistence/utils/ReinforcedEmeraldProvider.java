package com.kleinercode.fabric.persistence.utils;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

import java.util.List;

public class ReinforcedEmeraldProvider {

    public static final ItemStack REINFORCED_EMERALD;

    public static ItemStack createReinforcedEmerald() {
        ItemStack emerald = new ItemStack(Items.EMERALD, 1);
        emerald.set(DataComponentTypes.LORE, new LoreComponent(List.of(Text.literal("Persistent"))));
        emerald.set(DataComponentTypes.ITEM_NAME, Text.literal("Reinforced Emerald"));
        return emerald;
    }

    static {
        REINFORCED_EMERALD = createReinforcedEmerald();
    }

}
