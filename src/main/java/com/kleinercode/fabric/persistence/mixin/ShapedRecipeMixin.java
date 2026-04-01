package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.CraftItemCallback;
import com.kleinercode.fabric.persistence.Utils;
import com.kleinercode.fabric.persistence.utils.ItemStackWrapper;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Inject(method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void headOfCraft(CraftingInput craftingRecipeInput, CallbackInfoReturnable<ItemStack> ci) {

        ShapedRecipeAccessor accessor = (ShapedRecipeAccessor) this;
        ItemStackWrapper resultStack = new ItemStackWrapper(accessor.getResult().copy());
        InteractionResult result = CraftItemCallback.EVENT.invoker().interact(craftingRecipeInput, resultStack);

        if (result.equals(InteractionResult.FAIL)) {
            ci.setReturnValue(resultStack.itemStack);
        }

    }

}
