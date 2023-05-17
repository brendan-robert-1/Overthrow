package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.fights.farms.basic.DrunkMob;
import com.mygdx.game.encounters.fights.farms.basic.Homesteaders;
import com.mygdx.game.encounters.fights.farms.basic.SewerRats;
import com.mygdx.game.screens.widgets.fight.FightNode;

import java.util.Random;

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
        switch(new Random().nextInt(0,3)){
            case 0 -> { return new SewerRats();}
            case 1 -> {return new SewerRats();}
            case 2 -> {return new SewerRats();}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, 3));
        }
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
