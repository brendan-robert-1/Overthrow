package com.mygdx.game.encounters;

import com.mygdx.game.state.GameNode;

public class Chest extends GameNode {
    public Chest(){
        super(NodeType.CHEST, "Chest");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chest");
        return sb.toString();
    }
}
