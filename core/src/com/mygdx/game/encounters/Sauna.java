package com.mygdx.game.encounters;

import com.mygdx.game.state.GameNode;

public class Sauna extends GameNode {
    public Sauna(){
        super(NodeType.SAUNA, "Sauna");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sauna");
        return sb.toString();
    }
}
