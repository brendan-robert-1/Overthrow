package com.mygdx.game.encounters;

public class Chest extends Encounter{
    public Chest(){
        super(NodeType.CHEST);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Chest");
        return sb.toString();
    }
}
