package com.mygdx.game.screens.widgets.fight;

import com.mygdx.game.state.Character;

public interface TurnObserver {
    public static enum TurnEvent {
        TURN_STARTED,
        TURN_ENDED
    }
    void onNotify(String text, TurnEvent event);
}
