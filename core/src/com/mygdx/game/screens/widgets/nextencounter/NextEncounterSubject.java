package com.mygdx.game.screens.widgets.nextencounter;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.outfitter.OutfitterObserver;
import com.mygdx.game.state.GameNode;

public interface NextEncounterSubject {
    public void addObserver(NextEncounterObserver observer);
    public void removeObserver(NextEncounterObserver observer);
    public void removeAllObservers();
    public void notify(final GameNode.NodeType nodeSelected, NextEncounterObserver.NextEncounterEvent event);
}
