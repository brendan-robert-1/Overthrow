package com.mygdx.game.screens.encounterscreens.combat;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.screens.widgets.fight.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class CombatProcessor {
    private static final Logger logger = LogManager.getLogger(CombatProcessor.class);
    private static final int CT_THRESHOLD = 100;
    private Team team = Team.getInstance();
    private FightNode fight;

    public CombatProcessor(FightNode fight){
        this.fight = fight;
        setStartingChargeTime();
    }

    public void processFoeTurn(CharacterPanel activeCharacter){
        Timer.schedule( new Timer.Task(){
            @Override
            public void run() {
              //  logger.info("Processing foe turn");
                Ability ability = activeCharacter.getCharacter().getFirstBasicAbility(); //TODO pick random
                CharacterPanel target = findTargetFor(ability, false);
                fight.getEnemyTeam().animateAttack(activeCharacter);
                 ability.execute(target, activeCharacter, fight );
               // logger.info(activeCharacter.getName() + " has used " + ability.name() +" onto " + target.getName());
                increaseChargeTimeExcept(activeCharacter);
                activeCharacter.getCharacter().resetChargeTime();
                MainGameScreen.getInstance().updateScreenMidCombat();
                processEndOfTurnEvents();
            }
        },1f);
    }


    public void processEndOfTurnEvents(){
        logger.info("Scheduling end of turn events");
        new EndOfTurnRunnable(fight).run();
       // Timer.schedule(new EndOfTurnRunnable(fight),1f);

    }


    public void increaseChargeTimeExcept(CharacterPanel activeCharacter) {
        team.streamNonNull()
                .filter(c -> !c.equals(activeCharacter))
                .forEach(c -> c.getCharacter().increaseChargeTimeBy(CT_THRESHOLD));
        fight.getEnemyTeam().streamNonNull()
                .filter(c -> !c.equals(activeCharacter))
                .forEach(c -> c.getCharacter().increaseChargeTimeBy(CT_THRESHOLD));
    }



    private CharacterPanel findTargetFor(Ability ability, boolean isFriendly) {
        if(ability.offensiveTargetable()){
            return findOffensiveTarget(isFriendly);
        } else {
            return findFriendlyTarget(isFriendly); //TODO implement filter if non self targettable and no targets cast flail (3-5 damage to first character)
        }
    }

    private CharacterPanel findOffensiveTarget(boolean isFriendly) {
        if(isFriendly){
            return fight.getEnemyTeam().streamNonNull().findFirst().get();
        } else {
            return team.streamNonNull().findFirst().get();
        }
    }


    private CharacterPanel findFriendlyTarget(boolean isFriendly) {
        if(isFriendly){
            return  team.streamNonNull().findFirst().get();
        } else {
            return fight.getEnemyTeam().streamNonNull().findFirst().get();
        }
    }


    public CharacterPanel calculateActiveTurn(){
        CharacterPanel activeTurnAwardedTo = characterMaxCt();
        activeTurnAwardedTo.getCharacter().reduceCharTimeBy(CT_THRESHOLD);
        return activeTurnAwardedTo;
    }

    private CharacterPanel characterMaxCt(){
        CharacterPanel fastestCharacter = team.streamNonNull()
                .max(Comparator.comparing(CharacterPanel::getChargeTime))
                .get();
        CharacterPanel fastestEnemy = fight.getEnemyTeam().streamNonNull()
                .max(Comparator.comparing(CharacterPanel::getChargeTime))
                .get();
        if(fastestEnemy.compareTo(fastestCharacter) < 0){
            return fastestCharacter;
        }
        return fastestEnemy;
    }



    private boolean allCharactersDead(){
        for(CharacterPanel character : Team.getInstance().streamNonNull().toList()){
            if(character!= null && character.getCharacter().getHp() > 0){
                return false;
            }
        }
        return true;
    }

    private boolean allEnemiesDead(){
        boolean atLeastOneAlive = fight.getEnemyTeam().streamNonNull()
                .filter(c -> c.getCharacter().getHp()  >0)
                .anyMatch(c -> c.getCharacter().getHp() > 0);
        return !atLeastOneAlive;
    }

    private void setStartingChargeTime() {
      team.streamNonNull()
                .forEach(c -> c.getCharacter().setChargeTime(c.getCharacter().getStats().getSpeed()));
        fight.getEnemyTeam().streamNonNull()
                .forEach(c -> c.getCharacter().setChargeTime(c.getCharacter().getStats().getSpeed()));
    }

    public List<CharacterPanel> projectFutureTurnOrder(){
        List<CharacterPanel> turnOrders = new ArrayList<>();
        List<CharacterPanel> copyOfCharacters = new ArrayList<>();

        //copy current characters and populate temp list
        team.streamNonNull().forEach(c -> copyOfCharacters.add(CharacterPanel.from(c)));
        fight.getEnemyTeam().streamNonNull().forEach(e -> copyOfCharacters.add(CharacterPanel.from(e)));

        for(int i = 0; i < 10; i++){
            //find next active character and progress/reset turns
            CharacterPanel activeCharacter =  copyOfCharacters.stream().filter(Objects::nonNull)
                    .max(Comparator.comparing(CharacterPanel::getChargeTime))
                    .get();
            turnOrders.add(activeCharacter);
            activeCharacter.getCharacter().setChargeTime(0);
            copyOfCharacters.stream().filter(c -> !c.equals(activeCharacter)).forEach( c -> c.getCharacter().increaseChargeTimeBy(CT_THRESHOLD));
        }
        return turnOrders;
    }


    public boolean lost() {
        return allCharactersDead();
    }

    public boolean won(){
        return allEnemiesDead();
    }

}
