package com.mygdx.game.state;

import com.mygdx.game.encounters.OverthrowActs;

public abstract class GameNode {
    private NodeType nodeType;
    private OverthrowActs.ActType actType;
    private String displayName;


    public GameNode setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }





    public GameNode(NodeType nodeType, String displayName){
        this.nodeType = nodeType;
        this.displayName = displayName;
    }
    public GameNode(){}
    public NodeType getNodeType() {
        return nodeType;
    }



    public String getDisplayName() {
        return displayName;
    }



    public enum NodeType {
        OUTFITTER,
        BASIC_FIGHT,
        ELITE_FIGHT,
        BOSS_FIGHT,
        MARKET,
        ARMOR_MERCHANT,
        WEAPON_MERCHANT,
        BLACKSMITH,
        WISHING_WELL,
        SAUNA,
        ABILITY_TRAINER,
        GEM_MERCHANT,
        QUESTION_MARK,
        CHEST
    }

}
