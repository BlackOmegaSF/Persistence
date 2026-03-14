package com.kleinercode.fabric.persistence.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void endOfCopyFrom(ServerPlayer oldPlayer, boolean alive, CallbackInfo ci) {
        ((ServerPlayer)(Object)this).getInventory().replaceWith(oldPlayer.getInventory());

        //TODO Figure out why I do this
    }

}
