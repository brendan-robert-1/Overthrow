package com.mygdx.game.encounters;

public class Blacksmith extends Encounter{
    public Blacksmith(){
        super(NodeType.BLACKSMITH, "Blacksmith");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blacksmith");
        return sb.toString();
    }
}
