package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

import java.util.Objects;

public class Bloodlet implements Ability {

    private static final int REDUCE_DEBUFF_TURNS_BY = 3;
    private static final int DAMAGE = 3;

    @Override
    public String name() {
        return "Bloodlet";
    }



    @Override
    public String description() {
        return "Minor hp damage, 3 turns of debuff removal from target.";
    }



    @Override
    public boolean offensiveTargetable() {
        return false;
    }



    @Override
    public boolean friendlyTargetable() {
        return true;
    }



    @Override
    public boolean selfTargetable() {
        return true;
    }



    @Override
    public void execute(CharacterPanel target, CharacterPanel source, FightNode fight) {
        System.out.println("Bloodlet is executing on: " + target.getName());
        target.decreaseHpBy(DAMAGE);
        target.getBuffsBar().getDebuffs().stream().filter(Objects::nonNull).forEach(debuff -> {
            debuff.reduceTurnsRemaining(REDUCE_DEBUFF_TURNS_BY);
        });
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.BLOODLET;
    }
}

