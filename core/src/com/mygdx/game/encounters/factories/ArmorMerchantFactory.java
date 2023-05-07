package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.ArmorMerchant;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.GameNode;

public class ArmorMerchantFactory {

    public static GameNode generateRandomArmorMerchantFor(OverthrowActs.ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmQuestionMark(actType);}
            case BATTLEFIELD -> { return generateBattleFieldQuestionMark(actType);}
            case SWAMP -> {return generateSwampQuestionMark(actType);}
            case MOUNTAIN -> {return generateMountainQuestionMark(actType);}
            case PALACE -> {return generatePalaceQuestionMark(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }



    private static GameNode generatePalaceQuestionMark(OverthrowActs.ActType actType) {
        return new ArmorMerchant();
    }



    private static GameNode generateMountainQuestionMark(OverthrowActs.ActType actType) {
        return new ArmorMerchant();
    }



    private static GameNode generateSwampQuestionMark(OverthrowActs.ActType actType) {
        return new ArmorMerchant();
    }



    private static GameNode generateBattleFieldQuestionMark(OverthrowActs.ActType actType) {
        return new ArmorMerchant();
    }



    private static GameNode generateFarmQuestionMark(OverthrowActs.ActType actType) {
        return new ArmorMerchant();
    }
}
