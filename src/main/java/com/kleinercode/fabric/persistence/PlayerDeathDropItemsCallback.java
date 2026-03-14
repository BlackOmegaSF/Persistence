package com.kleinercode.fabric.persistence;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityEquipment;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

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
            (listeners) -> (mainInventory, equipment, player) -> {
                for (PlayerDeathDropItemsCallback listener : listeners) {
                    InteractionResult result = listener.interact(mainInventory, equipment, player);

                    if (result != InteractionResult.PASS) {
                        return result;
                    }
                }

                return InteractionResult.PASS;
            }
            );

    InteractionResult interact(NonNullList<ItemStack> mainInventory, EntityEquipment equipment, Player player);

}
