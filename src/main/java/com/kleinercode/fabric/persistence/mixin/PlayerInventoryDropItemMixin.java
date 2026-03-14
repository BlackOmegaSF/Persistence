package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.PlayerDeathDropItemsCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public abstract class PlayerInventoryDropItemMixin {

    @Inject(method = "dropAll", at = @At(value = "HEAD"), cancellable = true)
    private void onBeforeDropItems(CallbackInfo info) {

        PlayerInventoryAccessor accessor = (PlayerInventoryAccessor) this;
        InteractionResult result = PlayerDeathDropItemsCallback.EVENT.invoker().interact(accessor.getItems(), accessor.getEquipment(), accessor.getPlayer());

        if (result.equals(InteractionResult.FAIL)) {
            info.cancel();
        }
    }

}
