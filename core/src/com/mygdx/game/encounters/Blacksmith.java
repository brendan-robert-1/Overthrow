package com.mygdx.game.encounters;

public class Blacksmith extends GameNode {
    public Blacksmith(){
        super(NodeType.BLACKSMITH, "Blacksmith");
    }

    @Override
    public String ambientSounds() {
        return null;
    }
    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blacksmith");
        return sb.toString();
    }
}
