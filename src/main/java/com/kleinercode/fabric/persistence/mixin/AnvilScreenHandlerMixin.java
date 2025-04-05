package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.Utils;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.text.Text;
import net.minecraft.util.StringHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    @Shadow public abstract void updateResult();

    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, ForgingSlotsManager forgingSlotsManager) {
        super(type, syncId, playerInventory, context, forgingSlotsManager);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void insideUpdateResult(CallbackInfo ci) {

        ForgingScreenHandlerAccessor forgingScreenAccessor = (ForgingScreenHandlerAccessor) this;
        Inventory input = forgingScreenAccessor.getInput();

        // Check if first input slot is empty
        ItemStack firstSlot = input.getStack(0);
        if (firstSlot.isEmpty()) return;

        // Get second input slot, and get out if it's not a Reinforced Emerald
        ItemStack secondSlot = input.getStack(1);
        if (secondSlot.isEmpty() || !ItemStack.areEqual(secondSlot, Utils.REINFORCED_EMERALD)) return;

        // The second slot is a reinforced emerald, so copy the first input slot to modify safely
        ItemStack returnStack = firstSlot.copy();

        // Check for persistence, normal processing if already persistent
        if (Utils.checkForPersistence(returnStack)) return;

        // Add persistence to the output item
        Utils.addPersistence(returnStack);

        // Set the proper values for output to work properly
        AnvilScreenHandlerAccessor anvilScreenAccessor = (AnvilScreenHandlerAccessor) this;
        // Repair level cost
        anvilScreenAccessor.getLevelCost().set(1);
        // Repair item usage (not repairing, set 0)
        anvilScreenAccessor.setRepairItemUsage(0);
        // Item name (logic same as existing)
        String newNameInput = anvilScreenAccessor.getNewItemName();
        if (newNameInput != null && !StringHelper.isBlank(newNameInput)) {
            if (!newNameInput.equals(firstSlot.getName().getString())) {
                returnStack.set(DataComponentTypes.CUSTOM_NAME, Text.literal(newNameInput));
            }
        } else if (firstSlot.contains(DataComponentTypes.CUSTOM_NAME)) {
            returnStack.remove(DataComponentTypes.CUSTOM_NAME);
        }

        // Set output item and update screen
        forgingScreenAccessor.getOutput().setStack(0, returnStack);
        this.sendContentUpdates();

        // Cancel the rest of the method to prevent the rest of the logic from happening
        ci.cancel();

    }

}
