package com.mygdx.game.screens.widgets;

public interface ProceedObserver {
    public static enum ProceedEvent {
        PROCEED
    }
    void onNotify(ProceedEvent event );
}
