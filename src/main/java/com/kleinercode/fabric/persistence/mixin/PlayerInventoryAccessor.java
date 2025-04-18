package com.kleinercode.fabric.persistence.mixin;

import net.minecraft.entity.EntityEquipment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PlayerInventory.class)
public interface PlayerInventoryAccessor {
    @Accessor
    DefaultedList<ItemStack> getMain();

    @Accessor
    EntityEquipment getEquipment();

    @Accessor
    PlayerEntity getPlayer();
}
