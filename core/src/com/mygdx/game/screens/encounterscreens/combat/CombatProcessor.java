package com.mygdx.game.screens.encounterscreens.combat;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CombatProcessor {

    private static final int CT_THRESHOLD = 100;
    private CharacterSlots characterSlots;
    private FightNode fight;

    public CombatProcessor(FightNode fight){
        this.fight = fight;
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
        increaseChargeTimeExcept(enemyCharacter);
        enemyCharacter.resetChargeTime();
        increaseChargeTimeExcept(enemyCharacter);
    }
    private void processFoeTurn(Character activeCharacter){
        Ability ability = activeCharacter.getFirstBasicAbility(); //TODO pick random
        Character target = findTargetFor(ability, false);
        ability.execute(target, activeCharacter, fight );
    }



    public void increaseChargeTimeExcept(Character activeCharacter) {
        characterSlots.asList().stream()
                .filter(Objects::nonNull)
                .filter(c -> !c.equals(activeCharacter))
                .forEach(c -> c.increaseChargeTimeBy(CT_THRESHOLD));
        fight.getEnemyTeam().getEnemySlots().asList().stream()
                .filter(Objects::nonNull)
                .filter(c -> !c.equals(activeCharacter))
                .forEach(c -> c.increaseChargeTimeBy(CT_THRESHOLD));
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
            return fight.getEnemyTeam().getEnemySlots().asList().stream().filter(Objects::nonNull).findFirst().get();
        } else {
            return characterSlots.asList().stream().filter(Objects::nonNull).findFirst().get();
        }
    }


    private Character findFriendlyTarget(boolean isFriendly) {
        if(isFriendly){
            return characterSlots.asList().stream().filter(Objects::nonNull).findFirst().get();
        } else {
            return fight.getEnemyTeam().getEnemySlots().asList().stream().filter(Objects::nonNull).findFirst().get();
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
        Character fastestEnemy = fight.getEnemyTeam().getEnemySlots().asList().stream()
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
        boolean atLeastOneAlive = fight.getEnemyTeam().getEnemySlots().asList().stream().filter(Objects::nonNull)
                .filter(c -> c.getHp()  >0)
                .anyMatch(c -> c.getHp() > 0);
        return !atLeastOneAlive;
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
        fight.getEnemyTeam().getEnemySlots().asList().stream()
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


    public List<Character> projectFutureTurnOrder(){
        List<Character> turnOrders = new ArrayList<>();
        List<Character> copyOfCharacters = new ArrayList<>();

        //copy current characters and populate temp list
        GameState.getInstance().getCharacterSlots().asList().stream().filter(Objects::nonNull).forEach(c -> copyOfCharacters.add(new Character(c)));
        fight.getEnemyTeam().getEnemySlots().asList().stream().filter(Objects::nonNull).forEach(e -> copyOfCharacters.add(new Character(e)));

        for(int i = 0; i < 10; i++){
            //find next active character and progress/reset turns
            Character activeCharacter =  copyOfCharacters.stream().filter(Objects::nonNull)
                    .max(Comparator.comparing(Character::getChargeTime))
                    .get();
            turnOrders.add(activeCharacter);
            activeCharacter.setChargeTime(0);
            copyOfCharacters.stream().filter(c -> !c.equals(activeCharacter)).forEach( c -> c.increaseChargeTimeBy(CT_THRESHOLD));
        }
        return turnOrders;
    }



    public void executeAbility(Character targetCharacter, Ability ability) {
    }


    public boolean fightOver() {
        return allCharactersDead() || allEnemiesDead();
    }

    public boolean lost() {
        return allCharactersDead();
    }

    public boolean won(){
        return allEnemiesDead();
    }

    private void removeEmptyBuffs(){
        characterSlots.asList().forEach(character -> {
            removeEmptyBuffs(character);
            removeEmptyDebuffs(character);
        });
        fight.getEnemyTeam().getEnemySlots().asList().forEach(character -> {
            removeEmptyBuffs(character);
            removeEmptyDebuffs(character);
        });
    }

    private void processEndOfTurnDebuffFor(Character character) {
        if(character == null){return;}
        List<Buff> debuffs = character.getDebuffs();
        if(debuffs == null) {debuffs = new ArrayList<>();}
        debuffs.forEach(debuff -> {
            debuff.executeEndOfTurn(character);
            debuff.reduceTurnsRemaining(1);
        });
    }
    private void processEndOfTurnBuffFor(Character character) {
        if(character == null){return;}
        List<Buff> buffs = character.getBuffs();
        if(buffs == null) {buffs = new ArrayList<>();}
        buffs.forEach(buff -> {
            buff.executeEndOfTurn(character);
            buff.reduceTurnsRemaining(1);
        });
    }



    private void removeEmptyDebuffs(Character character) {
        if(character == null){return;}
        List<Buff> debuffs = character.getDebuffs();
        if(debuffs == null) {debuffs = new ArrayList<>();}
        List<Buff> filtered = debuffs.stream().filter(b -> b.turnsRemaining != 0).toList();
        ArrayList<Buff> arrayList = new ArrayList<>(filtered);
        character.setDebuffs(arrayList);
    }



    private void removeEmptyBuffs(Character character) {
        if(character == null){return;}
        List<Buff> buffs = character.getBuffs();
        if(buffs == null) {buffs = new ArrayList<>();}
        List<Buff> filtered = buffs.stream().filter(b -> b.turnsRemaining != 0).toList();
        ArrayList<Buff> arrayList = new ArrayList<>(filtered);
        character.setBuffs(arrayList);
    }





    public void processEndOfTurnEvents(){
        characterSlots.asList().forEach(character -> {
            processEndOfTurnDebuffFor(character);
            processEndOfTurnBuffFor(character);
        });
        fight.getEnemyTeam().getEnemySlots().asList().forEach(character -> {
            processEndOfTurnDebuffFor(character);
            processEndOfTurnBuffFor(character);
        });
        removeEmptyBuffs();
    }
}
