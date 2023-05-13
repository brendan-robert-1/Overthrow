package com.mygdx.game.screens.widgets;

import com.mygdx.game.screens.widgets.inventory.InventorySlot;
import com.mygdx.game.screens.widgets.inventory.InventorySlotObserver;

public interface BackgroundChangeSubject {
    public void notify(String backgroundAsset, BackgroundChangeObserver.BackgroundChange event);
}
