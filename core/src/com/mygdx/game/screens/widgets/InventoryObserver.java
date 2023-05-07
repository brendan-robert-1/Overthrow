package com.mygdx.game.screens.widgets;

public interface InventoryObserver {
    public static enum InventoryEvent {
        ITEM_ADDED,
        ITEM_REMOVED
    }
    void onNotify(final String value, InventoryEvent event);
}
