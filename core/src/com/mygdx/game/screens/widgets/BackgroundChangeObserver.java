package com.mygdx.game.screens.widgets;

import com.mygdx.game.screens.widgets.inventory.InventorySlot;
import com.mygdx.game.screens.widgets.inventory.InventorySlotObserver;

public interface BackgroundChangeObserver {
    public static enum BackgroundChange{
        ChangeBackground,
    }

    void onNotify(String backgroundAsset, BackgroundChange event);
}
