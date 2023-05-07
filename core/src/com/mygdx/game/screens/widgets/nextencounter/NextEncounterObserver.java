package com.mygdx.game.screens.widgets.nextencounter;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.outfitter.OutfitterObserver;
import com.mygdx.game.state.GameNode;

public interface NextEncounterObserver {
    public static enum NextEncounterEvent {
        NODE_SELECTED
    }
    void onNotify(GameNode.NodeType nodeSelected, NextEncounterEvent event);
}
