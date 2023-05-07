package com.mygdx.game.screens.widgets.outfitter;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventorySlot;
import com.mygdx.game.screens.widgets.inventory.InventorySlotObserver;

public interface OutfitterSubject {
    public void addObserver(OutfitterObserver outfitterObserver);
    public void removeObserver(OutfitterObserver outfitterObserver);
    public void removeAllObservers();
    public void notify(final InventoryItem itemSelected, OutfitterObserver.OutfitterEvent event);
}
