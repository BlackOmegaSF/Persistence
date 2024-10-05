package com.kleinercode.fabric.persistence.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerEntityMixin {

    @Inject(method = "copyFrom", at = @At("TAIL"))
    private void endOfCopyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci) {
        ((ServerPlayerEntity)(Object)this).getInventory().clone(oldPlayer.getInventory());
    }

}
