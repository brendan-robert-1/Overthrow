package com.mygdx.game.screens.widgets.fight;

import com.mygdx.game.state.Character;

public interface TurnSubject {
    public void addObserver(TurnObserver observer);
    public void removeObserver(TurnObserver observer);
    public void removeAllObservers();
    public void notify(final String eventd, java.util.List<Character> futureTurns, TurnObserver.TurnEvent event);
}
