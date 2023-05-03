package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.Chest;
import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.encounters.OverthrowActs;

public class ChestFactory {
    public static Encounter generateMidActChestFor(OverthrowActs.ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmQuestionMark(actType);}
            case BATTLEFIELD -> { return generateBattleFieldQuestionMark(actType);}
            case SWAMP -> {return generateSwampQuestionMark(actType);}
            case MOUNTAIN -> {return generateMountainQuestionMark(actType);}
            case PALACE -> {return generatePalaceQuestionMark(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    public static Encounter generateRandomChestFor(OverthrowActs.ActType actType) {
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
        return new Chest();
    }



    private static Encounter generateMountainQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }



    private static Encounter generateSwampQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }



    private static Encounter generateBattleFieldQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }



    private static Encounter generateFarmQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }
}
