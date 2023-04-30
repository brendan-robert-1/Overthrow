package com.mygdx.game.screens.encounterscreens;

import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class EncounterScreenFactory {
    public static OverthrowScreenAdapter getScreenFor(GameState gameState){
        switch(gameState.currentNode().getNodeType()){
            case OUTFITTER -> { return new OutfitterScreen(gameState);}
            case BASIC_FIGHT, BOSS_FIGHT -> { return new FightScreen(gameState);}
            case MARKET -> { return new MarketScreen(gameState);}
            case ARMOR_MERCHANT -> {return new ArmorMerchantScreen(gameState);}
            case WEAPON_MERCHANT -> {return new WeaponMerchantScreen(gameState);}
            case BLACKSMITH -> {return new BlacksmithScreen(gameState);}
            case WISHING_WELL -> {return new WishingWellScreen(gameState);}
            case SAUNA -> {return new SaunaScreen(gameState);}
            case ABILITY_TRAINER -> {return new AbilityTrainerScreen(gameState);}
            case GEM_MERCHANT -> {return new GemMerchantScreen(gameState);}
            case CHEST -> {return new ChestScreen(gameState);}
            default -> throw new IllegalStateException("Unexpected value: " + gameState.currentNode().getNodeType());
        }
    }
}
