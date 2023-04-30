package com.mygdx.game.state;

import com.mygdx.game.encounters.*;
import com.mygdx.game.encounters.OverthrowActs.ActType;
import com.mygdx.game.encounters.chancetables.FarmsEncounterChanceTable;
import com.mygdx.game.encounters.factories.*;

public class NodeGenerator {
    public static GameNode generateRandomNode(ActType actType, int floorNumber){
        switch(actType){
            case FARMS -> {return generateRandomFarmsNode(actType, floorNumber);}
            case BATTLEFIELD -> {return generateRandomBattlefieldNode(actType, floorNumber);}
            case SWAMP -> {return generateRandomSwampNode(actType, floorNumber);}
            case MOUNTAIN -> {return generateRandomMountainNode(actType, floorNumber);}
            case PALACE -> {return generateRandomPalaceNode(actType, floorNumber);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }

    private static GameNode generateRandomFarmsNode(ActType actType, int floorNumber){
       GameNode.NodeType nodeType = new FarmsEncounterChanceTable().generateRandomNode(floorNumber);
       switch(nodeType){
           case OUTFITTER -> {return new Outfitter();}
           case BASIC_FIGHT -> {return FightFactory.generateRandomFightFor(actType);}
           case ELITE_FIGHT -> {return FightFactory.generateRandomEliteFightFor(actType);}
           case BOSS_FIGHT -> {return BossFightFactory.generateRandomBossFightFor(actType);}
           case MARKET -> {return MarketFactory.generateRandomMarketFor(actType);}
           case ARMOR_MERCHANT -> {return ArmorMerchantFactory.generateRandomArmorMerchantFor(actType);}
           case WEAPON_MERCHANT -> {return WeaponMerchantFactory.generateRandomWeaponMerchantFor(actType);}
           case BLACKSMITH -> {return BlacksmithFactory.generateRandomBlacksmithFor(actType);}
           case WISHING_WELL -> {return WishingWellFactory.generateRandomWishingWellFor(actType);}
           case SAUNA -> {return new Sauna();}
           case ABILITY_TRAINER -> {return AbilityTrainerFactory.generateRandomAbilityTrainerFor(actType);}
           case GEM_MERCHANT -> {return GemMerchantFactory.generateRandomGemMerchantFor(actType);}
           case QUESTION_MARK -> {return QuestionMarkFactory.generateRandomQuestionMarkFor(actType);}
           case CHEST ->  {return ChestFactory.generateMidActChestFor(actType);}
           default -> throw new IllegalStateException("Unexpected value: " + nodeType);
       }
    }

    private static GameNode generateRandomBattlefieldNode(ActType actType, int floorNumber){
        return new Market();
    }
    private static GameNode generateRandomSwampNode(ActType actType, int floorNumber){
        return new Market();
    }
    private static GameNode generateRandomMountainNode(ActType actType, int floorNumber){
        return new Market();
    }
    private static GameNode generateRandomPalaceNode(ActType actType, int floorNumber){
        return new Market();
    }

}
