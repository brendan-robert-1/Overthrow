package com.mygdx.game.combat;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.screens.state.*;
import com.mygdx.game.screens.state.Character;
import com.mygdx.game.screens.state.items.ItemSlot;
import com.mygdx.game.screens.state.items.ItemSlotFactory;
import com.mygdx.game.screens.state.items.ItemType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CombatProcessor {

    private static final int CT_THRESHOLD = 100;
    private EnemySlots enemySlots;
    private CharacterSlots characterSlots;

    public CombatRewards processCombat(Fight fight){
        enemySlots = fight.startingUnits();
        characterSlots = GameState.getInstance().getCharacterSlots();
        setStartingChargeTime();
        while(!fightOver()){
            processTurn();
        }
        resetChargeTime();
        return combatRewards(fight);
    }

    private boolean fightOver() {
        return allCharactersDead() || allEnemiesDead();
    }

    private void processTurn(){
        Character activeCharacter = calculateActiveTurn();
        if(isFriendlyTurn(activeCharacter)){
            processFriendlyTurn(activeCharacter);
        } else {
            processFoeTurn(activeCharacter);
        }
    }

    //TODO find out how to get ability from UI
    private void processFriendlyTurn(Character activeCharacter){
        Ability ability = activeCharacter.getFirstBasicAbility();
        Character target = findTargetFor(ability, true);
    }

    private void processFoeTurn(Character activeCharacter){
        Ability ability = activeCharacter.getFirstBasicAbility(); //TODO pick random
        Character target = findTargetFor(ability, false);
    }



    private Character findTargetFor(Ability ability, boolean isFriendly) {
        if(ability.offensiveTargetable()){
            return findOffensiveTarget(isFriendly);
        } else {
            return findFriendlyTarget(isFriendly); //TODO implement filter if non self targettable and no targets cast flail (3-5 damage to first character)
        }
    }


    private Character findOffensiveTarget(boolean isFriendly) {
        return null;
    }


    private Character findFriendlyTarget(boolean isFriendly) {
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


    private Character calculateActiveTurn(){
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
        if(fastestEnemy.compareTo(fastestCharacter) > 0){
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
    private CombatRewards combatRewards(Fight fight) {
        CombatRewards rewards = new CombatRewards();
        List<ItemSlot> itemRewards = new ArrayList<>();
        itemRewards.add(ItemSlotFactory.one(ItemType.LEATHER_PANTS));
        itemRewards.add(ItemSlotFactory.one(ItemType.MINOR_HEALTH_POT));
        rewards.setItemRewards(itemRewards);
        rewards.setCoins(18);
        return rewards;
    }



    private void setStartingChargeTime() {
        characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .forEach(c -> {
                    c.setChargeTime(c.getBaseStats().getSpeed());
                });
    }
    private void resetChargeTime() {
        characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .forEach(c -> {
                    c.setChargeTime(0);
                });
    }




}
