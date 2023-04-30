package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.encounters.GemMerchant;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.encounters.OverthrowActs;

public class GemMerchantFactory {
    public static Encounter generateRandomGemMerchantFor(OverthrowActs.ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmQuestionMark(actType);}
            case BATTLEFIELD -> { return generateBattleFieldQuestionMark(actType);}
            case SWAMP -> {return generateSwampQuestionMark(actType);}
            case MOUNTAIN -> {return generateMountainQuestionMark(actType);}
            case PALACE -> {return generatePalaceQuestionMark(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }



    private static Encounter generatePalaceQuestionMark(OverthrowActs.ActType actType) {
        return new GemMerchant();
    }



    private static Encounter generateMountainQuestionMark(OverthrowActs.ActType actType) {
        return new GemMerchant();
    }



    private static Encounter generateSwampQuestionMark(OverthrowActs.ActType actType) {
        return new GemMerchant();
    }



    private static Encounter generateBattleFieldQuestionMark(OverthrowActs.ActType actType) {
        return new GemMerchant();
    }



    private static Encounter generateFarmQuestionMark(OverthrowActs.ActType actType) {
        return new GemMerchant();
    }
}
