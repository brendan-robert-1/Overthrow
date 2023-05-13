package com.mygdx.game.encounters;

public class Sauna extends GameNode {


    @Override
    public String ambientSounds() {
        return null;
    }
    public Sauna(){
        super(NodeType.SAUNA, "Sauna");
    }
    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sauna");
        return sb.toString();
    }
}
