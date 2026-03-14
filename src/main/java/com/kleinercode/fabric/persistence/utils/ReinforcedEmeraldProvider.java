package com.kleinercode.fabric.persistence.utils;

import java.util.List;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;

public class ReinforcedEmeraldProvider {

    public static final ItemStack REINFORCED_EMERALD;

    public static ItemStack createReinforcedEmerald() {
        ItemStack emerald = new ItemStack(Items.EMERALD, 1);
        emerald.set(DataComponents.LORE, new ItemLore(List.of(Component.literal("Persistent"))));
        emerald.set(DataComponents.ITEM_NAME, Component.literal("Reinforced Emerald"));
        return emerald;
    }

    static {
        REINFORCED_EMERALD = createReinforcedEmerald();
    }

}
