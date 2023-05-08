package com.mygdx.game.screens.widgets.nextencounter;

import com.mygdx.game.state.GameNode;

public interface PathSelectedSubject {
    public void addObserver(PathSelectedObserver observer);
    public void removeObserver(PathSelectedObserver observer);
    public void removeAllObservers();
    public void notify(final PathSelectedObserver.PathSelectedEvent event);
}
