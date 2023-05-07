package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.WeaponMerchant;
import com.mygdx.game.state.GameNode;

public class WeaponMerchantFactory {

    public static GameNode generateRandomWeaponMerchantFor(OverthrowActs.ActType actType) {
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
        return new WeaponMerchant();
    }



    private static GameNode generateMountainQuestionMark(OverthrowActs.ActType actType) {
        return new WeaponMerchant();
    }



    private static GameNode generateSwampQuestionMark(OverthrowActs.ActType actType) {
        return new WeaponMerchant();
    }



    private static GameNode generateBattleFieldQuestionMark(OverthrowActs.ActType actType) {
        return new WeaponMerchant();
    }



    private static GameNode generateFarmQuestionMark(OverthrowActs.ActType actType) {
        return QuestionMarkFactory.generateRandomQuestionMarkFor(actType);
    }
}
