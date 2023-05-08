package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.fights.Homesteaders;
import com.mygdx.game.screens.widgets.fight.FightNode;

public class FightFactory {
    public static FightNode generateRandomFightFor(OverthrowActs.ActType actType){
        switch(actType){
            case FARMS -> {return generateFarmFight();}
            case BATTLEFIELD -> { return generateBattlefieldFight();}
            case SWAMP -> {return generateSwampFight();}
            case MOUNTAIN -> {return generateMountainFight();}
            case PALACE -> {return generatePalaceFight();}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    public static FightNode generateRandomEliteFightFor(OverthrowActs.ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmFight();}
            case BATTLEFIELD -> { return generateBattlefieldFight();}
            case SWAMP -> {return generateSwampFight();}
            case MOUNTAIN -> {return generateMountainFight();}
            case PALACE -> {return generatePalaceFight();}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    private static FightNode generateFarmFight(){
        return new Homesteaders();
    }
    private static FightNode generateBattlefieldFight(){
        return new Homesteaders();
    }
    private static FightNode generateSwampFight(){
        return new Homesteaders();
    }
    private static FightNode generateMountainFight(){
        return new Homesteaders();
    }
    private static FightNode generatePalaceFight(){
        return new Homesteaders();
    }
}
