package com.kleinercode.fabric.persistence.mixin;

import com.kleinercode.fabric.persistence.Utils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShapedRecipe.class)
public abstract class ShapedRecipeMixin {

    @Inject(method = "craft(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void headOfCraft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup, CallbackInfoReturnable<ItemStack> ci) {

        // Manually check for valid reinforced emerald input
        if (craftingRecipeInput.getHeight() != 3) return;
        if (craftingRecipeInput.getWidth() != 3) return;
        List<ItemStack> stacks = craftingRecipeInput.getStacks();
        if (stacks.size() < 9) return;
        if (!stacks.get(0).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(1).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(2).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(3).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(4).isOf(Items.EMERALD)) return;
        if (!stacks.get(5).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(6).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(7).isOf(Items.IRON_INGOT)) return;
        if (!stacks.get(8).isOf(Items.IRON_INGOT)) return;

        // We're crafting the emerald, so return the custom item
        ci.setReturnValue(Utils.REINFORCED_EMERALD.copy());
    }

}
