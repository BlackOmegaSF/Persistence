package com.kleinercode.fabric.persistence;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.collection.DefaultedList;

import java.util.List;

public interface PlayerDeathDropItemsCallback {

    /**
     * Callback when player is dropping all items on death.
     * This is called before any item processing is done, but after keepInventory and spectator mode are checked.
     * Curse of Vanishing items are also checked before this event.
     * Upon return:
     *  - SUCCESS cancels further processing and continues with dropping all items
     *  - PASS falls back to further processing and defaults to SUCCESS if no other listeners are available
     *  - FAIL cancels further processing and does not drop any items
     */

    Event<PlayerDeathDropItemsCallback> EVENT = EventFactory.createArrayBacked(PlayerDeathDropItemsCallback.class,
            (listeners) -> (list, player) -> {
                for (PlayerDeathDropItemsCallback listener : listeners) {
                    ActionResult result = listener.interact(list, player);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            }
            );

    ActionResult interact(List<DefaultedList<ItemStack>> list, PlayerEntity player);

}
