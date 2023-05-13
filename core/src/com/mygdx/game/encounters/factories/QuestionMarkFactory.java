package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.OverthrowActs.ActType;
import com.mygdx.game.encounters.GameNode;
import com.mygdx.game.encounters.GameNode.NodeType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.mygdx.game.encounters.GameNode.NodeType.*;

public class QuestionMarkFactory {

    private static final List<NodeType> ENCOUNTERS_QUESTION_MARK = Arrays.asList(
            BASIC_FIGHT,
            MARKET,
            WISHING_WELL,
            CHEST
    );
    public static GameNode generateRandomQuestionMarkFor(ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmQuestionMark(actType);}
            case BATTLEFIELD -> { return generateBattleFieldQuestionMark(actType);}
            case SWAMP -> {return generateSwampQuestionMark(actType);}
            case MOUNTAIN -> {return generateMountainQuestionMark(actType);}
            case PALACE -> {return generatePalaceQuestionMark(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    //TODO specify specific question marks
    private static GameNode generateFarmQuestionMark(ActType actType) {
        switch(new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1)){
            case 0 -> {
                GameNode encounter = FightFactory.generateRandomFightFor(actType);
                encounter.setDisplayName("?");
                return encounter;
            }
            case 1 -> {
                GameNode encounter = MarketFactory.generateRandomMarketFor(actType);
                encounter.setDisplayName("?");
                return encounter;}
            case 2 -> {
                GameNode encounter = WishingWellFactory.generateRandomWishingWellFor(actType);
                encounter.setDisplayName("?");
                return encounter;}
            case 3 -> {
                GameNode encounter = ChestFactory.generateRandomChestFor(actType);
                encounter.setDisplayName("?");
                return encounter;}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1));
        }
    }


    //TODO specify specific question marks
    private static GameNode generatePalaceQuestionMark(ActType actType) {
        switch(new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1)){
            case 0 -> {return FightFactory.generateRandomFightFor(actType);}
            case 1 -> { return MarketFactory.generateRandomMarketFor(actType);}
            case 2 -> { return WishingWellFactory.generateRandomWishingWellFor(actType);}
            case 3 -> {return ChestFactory.generateMidActChestFor(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1));
        }
    }



    //TODO specify specific question marks
    private static GameNode generateMountainQuestionMark(ActType actType) {
        switch(new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1)){
            case 0 -> { return FightFactory.generateRandomFightFor(actType);}
            case 1 -> { return MarketFactory.generateRandomMarketFor(actType);}
            case 2 -> { return WishingWellFactory.generateRandomWishingWellFor(actType);}
            case 3 -> {return ChestFactory.generateMidActChestFor(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1));
        }
    }


    //TODO specify specific question marks
    private static GameNode generateSwampQuestionMark(ActType actType) {
        switch(new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1)){
            case 0 -> { return FightFactory.generateRandomFightFor(actType);}
            case 1 -> { return MarketFactory.generateRandomMarketFor(actType);}
            case 2 -> { return WishingWellFactory.generateRandomWishingWellFor(actType);}
            case 3 -> {return ChestFactory.generateMidActChestFor(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1));
        }
    }


    //TODO specify specific question marks
    private static GameNode generateBattleFieldQuestionMark(ActType actType) {
        switch(new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1)){
            case 0 -> { return FightFactory.generateRandomFightFor(actType);}
            case 1 -> { return MarketFactory.generateRandomMarketFor(actType);}
            case 2 -> { return WishingWellFactory.generateRandomWishingWellFor(actType);}
            case 3 -> {return ChestFactory.generateMidActChestFor(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, ENCOUNTERS_QUESTION_MARK.size() - 1));
        }
    }
}
