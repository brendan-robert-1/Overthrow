package com.mygdx.game.screens.widgets.fight;

import com.mygdx.game.state.Character;

public interface TurnSubject {

    public void notify(final String eventd, TurnObserver.TurnEvent event);
}
