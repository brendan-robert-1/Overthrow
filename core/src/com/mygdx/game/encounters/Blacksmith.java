package com.mygdx.game.encounters;

import com.mygdx.game.state.GameNode;

public class Blacksmith extends GameNode {
    public Blacksmith(){
        super(NodeType.BLACKSMITH, "Blacksmith");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blacksmith");
        return sb.toString();
    }
}
