package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.CraftItemCallback;
import com.kleinercode.fabric.persistence.Utils;
import com.kleinercode.fabric.persistence.utils.ItemStackWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Inject(method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void headOfCraft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup, CallbackInfoReturnable<ItemStack> ci) {

        ShapedRecipeAccessor accessor = (ShapedRecipeAccessor) this;
        ItemStackWrapper resultStack = new ItemStackWrapper(accessor.getResult().copy());
        ActionResult result = CraftItemCallback.EVENT.invoker().interact(craftingRecipeInput, resultStack);

        if (result.equals(ActionResult.FAIL)) {
            ci.setReturnValue(resultStack.itemStack);
        }

    }

}
