package com.mygdx.game.screens.widgets.nextencounter;

import com.mygdx.game.state.GameNode;

public interface PathSelectedObserver {
    public static enum PathSelectedEvent {
        PATH_SELECTED
    }
    public void onNotify(PathSelectedEvent event);
}
