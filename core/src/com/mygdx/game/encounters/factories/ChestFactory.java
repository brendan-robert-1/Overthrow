package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.Chest;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.GameNode;

public class ChestFactory {
    public static GameNode generateMidActChestFor(OverthrowActs.ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmQuestionMark(actType);}
            case BATTLEFIELD -> { return generateBattleFieldQuestionMark(actType);}
            case SWAMP -> {return generateSwampQuestionMark(actType);}
            case MOUNTAIN -> {return generateMountainQuestionMark(actType);}
            case PALACE -> {return generatePalaceQuestionMark(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    public static GameNode generateRandomChestFor(OverthrowActs.ActType actType) {
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
        return new Chest();
    }



    private static GameNode generateMountainQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }



    private static GameNode generateSwampQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }



    private static GameNode generateBattleFieldQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }



    private static GameNode generateFarmQuestionMark(OverthrowActs.ActType actType) {
        return new Chest();
    }
}
