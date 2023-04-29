package com.mygdx.game.screens.encounterscreens;

import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class EncounterScreenFactory {
    public static OverthrowScreenAdapter getScreenFor(GameState gameState){
        switch(gameState.currentNode().getNodeType()){
            case OUTFITTER -> { return new OutfitterScreen(gameState);}
            case FIGHT, BOSS_FIGHT -> { return new FightScreen(gameState);}
            case MARKET -> { return new MarketScreen(gameState);}
            case ARMOR_MERCHANT -> {return new MarketScreen(gameState);}
            case WEAPON_MERCHANT -> {return new MarketScreen(gameState);}
            case BLACKSMITH -> {return new MarketScreen(gameState);}
            case WISHING_WELL -> {return new WishingWellScreen(gameState);}
            case SAUNA -> {return new MarketScreen(gameState);}
            case ABILITY_TRAINER -> {return new MarketScreen(gameState);}
            case GEM_MERCHANT -> {return new MarketScreen(gameState);}
            default -> throw new IllegalStateException("Unexpected value: " + gameState.currentNode().getNodeType());
        }
    }
}
