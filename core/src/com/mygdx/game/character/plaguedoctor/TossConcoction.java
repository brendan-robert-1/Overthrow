package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

public class TossConcoction implements Ability {

    @Override
    public String name() {
        return "Toss Concoction";
    }



    @Override
    public String description() {
        return "AOE debuffing attack";
    }



    @Override
    public boolean offensiveTargetable() {
        return true;
    }



    @Override
    public boolean friendlyTargetable() {
        return false;
    }



    @Override
    public boolean selfTargetable() {
        return false;
    }




    @Override
    public void execute(Character target, Character source, FightNode fight) {
        System.out.println("Toss Concoction is executing on: " + target.getName());
        target.decreaseHpBy(15);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.TOSS_CONCOCTION;
    }
}
