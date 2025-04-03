package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.PlayerDeathDropItemsCallback;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryDropItemMixin {

    @Inject(method = "dropAll", at = @At(value = "HEAD"), cancellable = true)
    private void onBeforeDropItems(CallbackInfo info) {

        PlayerInventoryAccessor accessor = (PlayerInventoryAccessor) this;
        ActionResult result = PlayerDeathDropItemsCallback.EVENT.invoker().interact(accessor.getMain(), accessor.getEquipment(), accessor.getPlayer());

        if (result.equals(ActionResult.FAIL)) {
            info.cancel();
        }
    }

}
