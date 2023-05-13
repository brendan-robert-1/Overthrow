package com.mygdx.game.screens.widgets.nextencounter;

import com.mygdx.game.encounters.GameNode;

public interface NextEncounterObserver {
    public static enum NextEncounterEvent {
        NODE_SELECTED
    }
    void onNotify(GameNode.NodeType nodeSelected, NextEncounterEvent event);
}
