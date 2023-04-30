package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.encounters.OverthrowActs.ActType;
import com.mygdx.game.encounters.fights.Fight;

public class QuestionMarkFactory {
    public static Encounter generateRandomQuestionMarkFor(ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmQuestionMark(actType);}
            case BATTLEFIELD -> { return generateBattleFieldQuestionMark(actType);}
            case SWAMP -> {return generateSwampQuestionMark(actType);}
            case MOUNTAIN -> {return generateMountainQuestionMark(actType);}
            case PALACE -> {return generatePalaceQuestionMark(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }



    private static Encounter generatePalaceQuestionMark(ActType actType) {
        return new Market();
    }



    private static Encounter generateMountainQuestionMark(ActType actType) {
        return new Market();
    }



    private static Encounter generateSwampQuestionMark(ActType actType) {
        return new Market();
    }



    private static Encounter generateBattleFieldQuestionMark(ActType actType) {
        return new Market();
    }



    private static Encounter generateFarmQuestionMark(ActType actType) {
        return new Market();
    }
}
