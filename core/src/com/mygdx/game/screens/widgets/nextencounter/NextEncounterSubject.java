package com.mygdx.game.screens.widgets.nextencounter;

import com.mygdx.game.encounters.GameNode;

public interface NextEncounterSubject {
    public void addObserver(NextEncounterObserver observer);
    public void removeObserver(NextEncounterObserver observer);
    public void removeAllObservers();
    public void notify(final GameNode.NodeType nodeSelected, NextEncounterObserver.NextEncounterEvent event);
}
