package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.state.GameState;

public class EncounterScreenFactory {
    public static ScreenAdapter getScreenFor(){
        switch(GameState.getInstance().getCurrentNode().getNodeType()){
            case OUTFITTER -> { return MainGameScreen.getInstance();}
            case BASIC_FIGHT, BOSS_FIGHT -> { return new FightScreen();}
            case MARKET -> { return new MarketScreen();}
            case ARMOR_MERCHANT -> {return new ArmorMerchantScreen();}
            case WEAPON_MERCHANT -> {return new WeaponMerchantScreen();}
            case BLACKSMITH -> {return new BlacksmithScreen();}
            case WISHING_WELL -> {return new WishingWellScreen();}
            case SAUNA -> {return new SaunaScreen();}
            case ABILITY_TRAINER -> {return new AbilityTrainerScreen();}
            case GEM_MERCHANT -> {return new GemMerchantScreen();}
            case CHEST -> {return new ChestScreen();}
            default -> throw new IllegalStateException("Unexpected value: " + GameState.getInstance().getCurrentNode().getNodeType());
        }
    }
}
