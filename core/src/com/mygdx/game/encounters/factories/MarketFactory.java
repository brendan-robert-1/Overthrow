package com.mygdx.game.encounters.factories;

import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.encounters.OverthrowActs.ActType;
import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.items.ItemSlotFactory;
import com.mygdx.game.state.items.ItemType;
import com.mygdx.game.state.shops.MarketOffering;
import com.mygdx.game.state.shops.MarketOfferingFactory;
import com.mygdx.game.state.shops.PotentialOffering;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MarketFactory {
    public static Encounter generateRandomMarketFor(ActType actType) {
        switch(actType){
            case FARMS -> {return generateFarmMarket(actType);}
            case BATTLEFIELD -> { return generateBattleFieldMarket(actType);}
            case SWAMP -> {return generateSwampMarket(actType);}
            case MOUNTAIN -> {return generateMountainMarket(actType);}
            case PALACE -> {return generatePalaceMarket(actType);}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }



    private static Encounter generatePalaceMarket(ActType actType) {
        Map<ItemSlot, Integer> wares = generatePalaceWares(actType);
        return new Market(wares);
    }






    private static Encounter generateMountainMarket(ActType actType) {
        Map<ItemSlot, Integer> wares = generateMountainWares(actType);
        return new Market(wares);
    }





    private static Encounter generateSwampMarket(ActType actType) {
        Map<ItemSlot, Integer> wares = generateSwampWares(actType);
        return new Market(wares);
    }





    private static Encounter generateBattleFieldMarket(ActType actType) {
        Map<ItemSlot, Integer> wares = generateBattleFieldWares(actType);
        return new Market(wares);
    }




    private static Encounter generateFarmMarket(ActType actType) {
        Map<ItemSlot, Integer> wares = generateFarmWares(actType);
        return new Market(wares);
    }

    private static Map<ItemSlot, Integer> generatePalaceWares(ActType actType) {
        return null;
    }

    private static Map<ItemSlot, Integer> generateMountainWares(ActType actType) {
        return null;
    }

    private static Map<ItemSlot, Integer> generateSwampWares(ActType actType) {
        return null;
    }


    private static Map<ItemSlot, Integer> generateBattleFieldWares(ActType actType) {
        return null;
    }


    private static Map<ItemSlot, Integer> generateFarmWares(ActType actType) {
        MarketOffering marketOffering = MarketOfferingFactory.getMarketOfferingFor(actType);
        return buildWaresFromPotential(marketOffering);
    }

    //TODO add a remove from offerings once selected so no duplicates
    private static Map<ItemSlot, Integer> buildWaresFromPotential(MarketOffering marketOffering){
        Map<ItemSlot, Integer> wares = new HashMap<>();
        PotentialOffering weapon1 = getRandomOffering(marketOffering.getPotentialWeapons());
        int priceWeapon1 = getRandomPrice(weapon1);
        int quantityWeapon1 = getRandomQuantity(weapon1);
        wares.put(buildItemFrom(weapon1, quantityWeapon1), priceWeapon1);

        PotentialOffering weapon2= getRandomOffering(marketOffering.getPotentialWeapons());
        int priceWeapon2 = getRandomPrice(weapon2);
        int quantityWeapon2 = getRandomQuantity(weapon2);
        wares.put(buildItemFrom(weapon2, quantityWeapon2), priceWeapon2);

        PotentialOffering armor1= getRandomOffering(marketOffering.getPotentialArmor());
        int priceArmor1 = getRandomPrice(armor1);
        int quantityArmor1 = getRandomQuantity(armor1);
        wares.put(buildItemFrom(armor1, quantityArmor1), priceArmor1);

        PotentialOffering armor2= getRandomOffering(marketOffering.getPotentialArmor());
        int priceArmor2 = getRandomPrice(armor2);
        int quantityArmor2 = getRandomQuantity(armor2);
        wares.put(buildItemFrom(armor2, quantityArmor2),priceArmor2);

        PotentialOffering consumable1= getRandomOffering(marketOffering.getPotentialConsumables());
        int priceConsumable1 = getRandomPrice(consumable1);
        int quantityConsumable1 = getRandomQuantity(consumable1);
        wares.put(buildItemFrom(consumable1, quantityConsumable1),priceConsumable1);

        PotentialOffering consumable2= getRandomOffering(marketOffering.getPotentialConsumables());
        int priceConsumable2 = getRandomPrice(consumable2);
        int quantityConsumable2 = getRandomQuantity(consumable2);
        wares.put(buildItemFrom(consumable2, quantityConsumable2),priceConsumable2);

        PotentialOffering gem = getRandomOffering(marketOffering.getPotentialGems());
        int priceGem = getRandomPrice(gem);
        int quantityGem = getRandomQuantity(gem);
        wares.put(buildItemFrom(gem, quantityGem),priceGem);

        return wares;
    }



    private static ItemSlot buildItemFrom(PotentialOffering potentialOffering, int quantity) {
        ItemType itemType = ItemType.from(potentialOffering.getItemName());
        return ItemSlotFactory.of(itemType, quantity);
    }



    private static int getRandomQuantity(PotentialOffering gem) {
        int min = gem.getQuantityMin();
        int max = gem.getQuantityMax();
        if(min == max){
            return min;
        }
        return new Random().nextInt(min, max);
    }



    private static int getRandomPrice(PotentialOffering gem) {
        int min = gem.getPriceMin();
        int max = gem.getPriceMax();
        if(min == max){
            return min;
        }
        return new Random().nextInt(min, max);
    }



    private static PotentialOffering getRandomOffering(List<PotentialOffering> potentialOfferings){
        return potentialOfferings.get(
                new Random().nextInt(potentialOfferings.size() - 1)
        );
    }
}
