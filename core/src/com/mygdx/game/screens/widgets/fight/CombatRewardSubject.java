package com.mygdx.game.screens.widgets.fight;

public interface CombatRewardSubject {
    void notify(CombatRewardSelectedObserver.RewardType type);
}
