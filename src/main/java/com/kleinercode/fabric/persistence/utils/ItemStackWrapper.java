package com.kleinercode.fabric.persistence.utils;

import net.minecraft.item.ItemStack;

public class ItemStackWrapper {
    public ItemStack itemStack;

    public ItemStackWrapper(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
}
