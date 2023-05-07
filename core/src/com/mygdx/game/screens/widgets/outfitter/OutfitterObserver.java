package com.mygdx.game.screens.widgets.outfitter;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryObserver;

public interface OutfitterObserver {
    public static enum OutfitterEvent {
        ITEM_SELECTED
    }
    void onNotify(InventoryItem itemSelected, OutfitterEvent event);
}
