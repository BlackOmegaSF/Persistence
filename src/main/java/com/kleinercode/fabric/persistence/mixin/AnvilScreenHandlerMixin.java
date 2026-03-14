package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.Utils;
import com.kleinercode.fabric.persistence.utils.ReinforcedEmeraldProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilScreenHandlerMixin extends ItemCombinerMenu {

    @Shadow public abstract void createResult();

    public AnvilScreenHandlerMixin(@Nullable MenuType<?> type, int syncId, Inventory playerInventory, ContainerLevelAccess context, ItemCombinerMenuSlotDefinition forgingSlotsManager) {
        super(type, syncId, playerInventory, context, forgingSlotsManager);
    }

    @Inject(method = "createResult", at = @At("HEAD"), cancellable = true)
    private void insideUpdateResult(CallbackInfo ci) {

        ForgingScreenHandlerAccessor forgingScreenAccessor = (ForgingScreenHandlerAccessor) this;
        Container input = forgingScreenAccessor.getInputSlots();

        // Check if first input slot is empty
        ItemStack firstSlot = input.getItem(0);
        if (firstSlot.isEmpty()) return;

        // Get second input slot, and get out if it's not a Reinforced Emerald
        ItemStack secondSlot = input.getItem(1);
        if (secondSlot.isEmpty() || !ItemStack.matches(secondSlot, ReinforcedEmeraldProvider.REINFORCED_EMERALD)) return;

        // The second slot is a reinforced emerald, so copy the first input slot to modify safely
        ItemStack returnStack = firstSlot.copy();

        // Check for persistence, normal processing if already persistent
        if (Utils.checkForPersistence(returnStack)) return;

        // Add persistence to the output item
        Utils.addPersistence(returnStack);

        // Set the proper values for output to work properly
        AnvilScreenHandlerAccessor anvilScreenAccessor = (AnvilScreenHandlerAccessor) this;
        // Repair level cost
        anvilScreenAccessor.getCost().set(1);
        // Repair item usage (not repairing, set 0)
        anvilScreenAccessor.setRepairItemUsage(0);
        // Item name (logic same as existing)
        String newNameInput = anvilScreenAccessor.getItemName();
        if (newNameInput != null && !StringUtil.isBlank(newNameInput)) {
            if (!newNameInput.equals(firstSlot.getHoverName().getString())) {
                returnStack.set(DataComponents.CUSTOM_NAME, Component.literal(newNameInput));
                anvilScreenAccessor.getCost().set(2);
            }
        } else if (firstSlot.has(DataComponents.CUSTOM_NAME)) {
            returnStack.remove(DataComponents.CUSTOM_NAME);
        }

        // Set output item and update screen
        forgingScreenAccessor.getResultSlots().setItem(0, returnStack);
        this.broadcastChanges();

        // Cancel the rest of the method to prevent the rest of the logic from happening
        ci.cancel();

    }

}
