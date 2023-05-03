package com.mygdx.game.screens.encounterscreens;

import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.encounters.state.GameState;

public class EncounterScreenFactory {
    public static OverthrowScreenAdapter getScreenFor(GameState gameState){
        switch(gameState.getCurrentNode().getNodeType()){
            case OUTFITTER -> { return new OutfitterScreen();}
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
            default -> throw new IllegalStateException("Unexpected value: " + gameState.getCurrentNode().getNodeType());
        }
    }
}
