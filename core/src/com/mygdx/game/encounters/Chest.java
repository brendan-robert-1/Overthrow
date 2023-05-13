package com.mygdx.game.encounters;

public class Chest extends GameNode {
    public Chest(){
        super(NodeType.CHEST, "Chest");
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
        final StringBuilder sb = new StringBuilder("Chest");
        return sb.toString();
    }
}
