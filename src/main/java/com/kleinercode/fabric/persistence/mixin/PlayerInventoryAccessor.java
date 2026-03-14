package com.kleinercode.fabric.persistence.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.EntityEquipment;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(Inventory.class)
public interface PlayerInventoryAccessor {
    @Accessor
    NonNullList<ItemStack> getItems();

    @Accessor
    EntityEquipment getEquipment();

    @Accessor
    Player getPlayer();
}
