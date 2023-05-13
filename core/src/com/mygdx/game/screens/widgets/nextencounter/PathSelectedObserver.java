package com.mygdx.game.screens.widgets.nextencounter;

public interface PathSelectedObserver {
    public static enum PathSelectedEvent {
        PATH_SELECTED
    }
    public void onNotify(PathSelectedEvent event);
}
