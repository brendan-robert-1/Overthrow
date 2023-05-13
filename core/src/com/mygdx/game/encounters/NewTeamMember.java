package com.mygdx.game.encounters;

public class NewTeamMember extends GameNode {


    @Override
    public String ambientSounds() {
        return null;
    }
    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }
    public NewTeamMember(NodeType nodeType, String displayName) {
        super(nodeType, displayName);
    }
}
