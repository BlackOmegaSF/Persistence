package com.kleinercode.fabric.persistence.mixin;

import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AnvilScreenHandler.class)
public interface AnvilScreenHandlerAccessor {

    @Accessor
    Property getLevelCost();

    @Accessor("repairItemUsage")
    void setRepairItemUsage(int repairItemUsage);

    @Accessor
    String getNewItemName();

}
