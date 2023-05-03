package com.mygdx.game.encounters;

import com.mygdx.game.encounters.state.GameNode;

public abstract class Encounter extends GameNode {
    public Encounter(NodeType nodeType, String displayName) {
        super(nodeType, displayName);
    }
    public Encounter(){}

}
