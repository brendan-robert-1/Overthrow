package com.mygdx.game.encounters;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Assets;

public abstract class GameNode extends Window {
    private NodeType nodeType;
    private OverthrowActs.ActType actType;
    private String displayName;
    public abstract String backgroundAsset();
    public abstract String ambientSounds();


    public GameNode(NodeType nodeType, String displayName){
        super(displayName, Assets.skin());
        this.nodeType = nodeType;
        this.displayName = displayName;
        this.setBackground((Drawable)null);
    }
    public GameNode(){
        super("Encounter", Assets.skin());
    }
    public NodeType getNodeType() {
        return nodeType;
    }

    public String getDisplayName() {
        return displayName;
    }


    public GameNode setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
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
        CHEST, KEYMASTER, ABANDONED_FARMHOUSE, TEMPLE_PRAYERS, PATH_SELECTION
    }

}
