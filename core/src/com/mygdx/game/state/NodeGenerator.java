package com.mygdx.game.state;

import com.mygdx.game.encounters.ArmorMerchant;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.OverthrowActs.ActType;
import com.mygdx.game.encounters.Sauna;
import com.mygdx.game.encounters.factories.*;

public class NodeGenerator {
    public static GameNode generateRandomNode(ActType actType){
        switch(actType){
            case FARMS -> {return generateRandomFarmsNode(actType);}
            case BATTLEFIELD -> {return generateRandomBattlefieldNode(actType);}
            case SWAMP -> {return generateRandomSwampNode(actType);}
            case MOUNTAIN -> {return generateRandomMountainNode(actType);}
            case PALACE -> {return generateRandomPalaceNode(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }



    private static GameNode generateRandomFarmsNode(ActType actType){
       GameNode.NodeType nodeType = new FarmsEncounterChanceTable().generateRandomNode();
       switch(nodeType){
           case OUTFITTER -> {return new Market();}
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
           default -> throw new IllegalStateException("Unexpected value: " + nodeType);
       }
    }

    private static GameNode generateRandomBattlefieldNode(ActType actType){
        return new Market();
    }
    private static GameNode generateRandomSwampNode(ActType actType){
        return new Market();
    }
    private static GameNode generateRandomMountainNode(ActType actType){
        return new Market();
    }
    private static GameNode generateRandomPalaceNode(ActType actType){
        return new Market();
    }

}
