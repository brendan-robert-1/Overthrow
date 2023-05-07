package com.mygdx.game.encounters.fights;

import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameNode;

import java.util.List;

public abstract class Fight extends GameNode {
    public Fight(NodeType nodeType, String displayName) {
        super(nodeType, displayName);
    }
    public Fight() {
    }



    public abstract List<OverthrowActs.ActType> actTypeEncounterable();
    public abstract EnemySlots startingUnits();
}
