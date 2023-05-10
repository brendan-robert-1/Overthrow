package com.mygdx.game.screens.widgets.fight;

public interface CombatRewardSelectedObserver {
    void onNotify(CombatRewardSelectedObserver.RewardType type);

    public enum RewardType {
        SELECTED
    }
}
