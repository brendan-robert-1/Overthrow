package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.encounters.fights.Homesteaders;

public class FightFactory {
    public static Fight generateRandomFightFor(OverthrowActs.ActType actType){
        switch(actType){
            case FARMS -> {return generateFarmFight();}
            case BATTLEFIELD -> { return generateBattlefieldFight();}
            case SWAMP -> {return generateSwampFight();}
            case MOUNTAIN -> {return generateMountainFight();}
            case PALACE -> {return generatePalaceFight();}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    public static Fight generateRandomEliteFightFor(OverthrowActs.ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmFight();}
            case BATTLEFIELD -> { return generateBattlefieldFight();}
            case SWAMP -> {return generateSwampFight();}
            case MOUNTAIN -> {return generateMountainFight();}
            case PALACE -> {return generatePalaceFight();}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    private static Fight generateFarmFight(){
        return new Homesteaders();
    }
    private static Fight generateBattlefieldFight(){
        return new Homesteaders();
    }
    private static Fight generateSwampFight(){
        return new Homesteaders();
    }
    private static Fight generateMountainFight(){
        return new Homesteaders();
    }
    private static Fight generatePalaceFight(){
        return new Homesteaders();
    }
}
