package com.kleinercode.fabric.persistence;

import com.kleinercode.fabric.persistence.utils.ItemStackWrapper;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.crafting.CraftingInput;

public interface CraftItemCallback {

    /**
     * Callback when an item is being looked up as a crafting result.
     * Upon return:
     *   - SUCCESS cancels further processing and continues with default logic
     *   - PASS falls back to further processing and defaults to SUCCESS if no other listeners are available
     *   - FAIL cancels further processing and returns the modified item as the crafting result
     */

    Event<CraftItemCallback> EVENT = EventFactory.createArrayBacked(CraftItemCallback.class,
            (listeners) -> (craftingRecipeInput, resultStack) -> {
        for (CraftItemCallback listener : listeners) {
            InteractionResult result = listener.interact(craftingRecipeInput, resultStack);

            if (result != InteractionResult.PASS) return result;
        }

        return InteractionResult.SUCCESS;
    });

    InteractionResult interact(CraftingInput craftingRecipeInput, ItemStackWrapper resultStack);

}
