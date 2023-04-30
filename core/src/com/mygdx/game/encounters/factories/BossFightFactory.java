package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.fights.*;

import java.util.Random;

public class BossFightFactory {
    public static Fight generateRandomBossFightFor(OverthrowActs.ActType actType){
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
        switch(new Random().nextInt(3)){
            case 0 -> {return new TaxMan();}
            case 1 -> {return new Fred();}
            case 2 -> {return new MutatedLivestock();}
            default -> throw new IllegalStateException("Unexpected value");
        }
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

