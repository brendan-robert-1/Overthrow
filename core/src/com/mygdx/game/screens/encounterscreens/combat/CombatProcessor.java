package com.mygdx.game.screens.encounterscreens.combat;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CombatProcessor {

    private static final int CT_THRESHOLD = 100;
    private EnemySlots enemySlots;
    private CharacterSlots characterSlots;

    public CombatProcessor(FightNode fight){
        enemySlots = fight.startingUnits();
        characterSlots = GameState.getInstance().getCharacterSlots();
        setStartingChargeTime();
    }

//    public CombatRewards processCombat(Fight fight){
//        enemySlots = fight.startingUnits();
//        characterSlots = GameState.getInstance().getCharacterSlots();
//        setStartingChargeTime();
//        while(!fightOver()){
//            processTurn();
//        }
//        resetChargeTime();
//        return combatRewards(fight);
//    }

    public void executeEnemyTurn(Character enemyCharacter) {
        processFoeTurn(enemyCharacter);
        if(!fightOver()){
            System.out.println("Fight over!...");
        }
        increaseChargeTimeExcept(enemyCharacter);
    }

    public void executeFriendlyTurn(Character friendlyCharacter, Ability ability){
        System.out.println("Executing ability: " + ability.name());
        increaseChargeTimeExcept(friendlyCharacter);
    }

    public boolean fightOver() {
        return allCharactersDead() || allEnemiesDead();
    }

    private void processTurn(){
        Character activeCharacter = calculateActiveTurn();
        //System.out.println("Fastest character is " + activeCharacter.getName() + " with CT of: " + activeCharacter.getChargeTime());
        if(isFriendlyTurn(activeCharacter)){
            processFriendlyTurn(activeCharacter);
        } else {
            processFoeTurn(activeCharacter);
        }
        increaseChargeTimeExcept(activeCharacter);
    }



    private void increaseChargeTimeExcept(Character activeCharacter) {
        characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .filter(c -> !c.equals(activeCharacter))
                .forEach(c -> c.increaseChargeTimeBy(CT_THRESHOLD));
        enemySlots.asList().stream()
                .filter(Objects::nonNull)
                .filter(c -> !c.equals(activeCharacter))
                .forEach(c -> c.increaseChargeTimeBy(CT_THRESHOLD));
    }



    //TODO find out how to get ability from UI
    private void processFriendlyTurn(Character activeCharacter){
        Ability ability = activeCharacter.getFirstBasicAbility();
        Character target = findTargetFor(ability, true);
        ability.execute(target, activeCharacter);
    }

    private void processFoeTurn(Character activeCharacter){
        Ability ability = activeCharacter.getFirstBasicAbility(); //TODO pick random
        Character target = findTargetFor(ability, false);
        ability.execute(target, activeCharacter);
    }

    private Character findTargetFor(Ability ability, boolean isFriendly) {
        if(ability.offensiveTargetable()){
            return findOffensiveTarget(isFriendly);
        } else {
            return findFriendlyTarget(isFriendly); //TODO implement filter if non self targettable and no targets cast flail (3-5 damage to first character)
        }
    }

    private Character findOffensiveTarget(boolean isFriendly) {
        if(isFriendly){
            return enemySlots.asList().stream().filter(Objects::nonNull).findFirst().get();
        } else {
            return characterSlots.asList().stream().filter(Objects::nonNull).findFirst().get();
        }
    }


    private Character findFriendlyTarget(boolean isFriendly) {
        if(isFriendly){
            return characterSlots.asList().stream().filter(Objects::nonNull).findFirst().get();
        } else {
            return enemySlots.asList().stream().filter(Objects::nonNull).findFirst().get();
        }
    }

    private boolean isFriendlyTurn(Character activeCharacter){
        if(characterSlots.asList().contains(activeCharacter)){
            return true;
        } else if (enemySlots.asList().contains(activeCharacter)){
            return false;
        } else {
            throw new IllegalStateException("Active character is not in the friendly or foe list. Invalid state keep debugging.");
        }
    }


    public Character calculateActiveTurn(){
        Character activeTurnAwardedTo = characterMaxCt();
        activeTurnAwardedTo.reduceCharTimeBy(CT_THRESHOLD);
        return activeTurnAwardedTo;
    }

    private Character characterMaxCt(){
        Character fastestCharacter = characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(Character::getChargeTime))
                .get();
        Character fastestEnemy = enemySlots.asList().stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(Character::getChargeTime))
                .get();
        if(fastestEnemy.compareTo(fastestCharacter) < 0){
            return fastestCharacter;
        }

        return fastestEnemy;
    }



    private boolean allCharactersDead(){
        for(Character character : GameState.getInstance().getCharacterSlots().asList()){
            if(character!= null && character.getHp() > 0){
                return false;
            }
        }
        return true;
    }

    private boolean allEnemiesDead(){
        for(Character character : enemySlots.asList()){
            if(character.getHp() > 0){
                return false;
            }
        }
        return true;
    }

    //TODO generate random rewards for a given fight type from json
    private CombatRewards combatRewards(FightNode fight) {
        CombatRewards rewards = new CombatRewards();
        List<InventoryItem> itemRewards = new ArrayList<>();
        itemRewards.add(InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.HIDE_SHIELD));
        rewards.setItemRewards(itemRewards);
        rewards.setCoins(18);
        System.out.println("Fight complete! Rewarding 18 coins, leather pants, and a minor health pot.");
        return rewards;
    }



    private void setStartingChargeTime() {
        characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .forEach(c -> c.setChargeTime(c.getStats().getSpeed()));
        enemySlots.asList().stream()
                .filter(Objects::nonNull)
                .forEach(c -> c.setChargeTime(c.getStats().getSpeed()));
    }
    public void resetChargeTime() {
        characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .forEach(c -> {
                    c.setChargeTime(0);
                });
    }

    public Character getNextActiveFriendly() {
        Character activeTurnAwardedTo = characterMaxCt();
        activeTurnAwardedTo.reduceCharTimeBy(CT_THRESHOLD);
        return activeTurnAwardedTo;
    }
}
