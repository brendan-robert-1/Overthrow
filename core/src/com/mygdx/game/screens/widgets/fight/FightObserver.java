package com.mygdx.game.screens.widgets.fight;

public interface FightObserver {
    public static enum FightEvent {
        FIGHT_STARTED,
        ENEMY_TURN_TAKEN,
        FIGHT_OVER
    }
    void onNotify(String text, FightEvent event);
}

