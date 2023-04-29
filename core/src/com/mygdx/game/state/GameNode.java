package com.mygdx.game.state;

public class GameNode {

    private NodeType nodeType;
    private GameNode(){}
    public GameNode(NodeType nodeType){
        this.nodeType = nodeType;
    }
    public NodeType getNodeType() {
        return nodeType;
    }
    public static GameNode of(NodeType nodeType){
        return new GameNode(nodeType);
    }

    public enum NodeType {
        OUTFITTER,
        FIGHT,
        BOSS_FIGHT,
        MARKET,
        ARMOR_MERCHANT,
        WEAPON_MERCHANT,
        BLACKSMITH,
        WISHING_WELL,
        SAUNA,
        ABILITY_TRAINER,
        GEM_MERCHANT
    }

}
