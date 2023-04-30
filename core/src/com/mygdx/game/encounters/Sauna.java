package com.mygdx.game.encounters;

public class Sauna extends Encounter{
    public Sauna(){
        super(NodeType.SAUNA);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sauna");
        return sb.toString();
    }
}
