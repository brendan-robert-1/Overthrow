package com.mygdx.game.character.knight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

public class Challenge implements Ability {
    @Override
    public String name() {
        return "Challenge";
    }



    @Override
    public String description() {
        return "Marks an opponent with a debuff that increases damage taken from the marker for 4 turns.";
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
    public void execute(CharacterPanel target, CharacterPanel source, FightNode fight) {
        System.out.println("Challenge is being executed on: " + source.getName() + " onto: " + target.getName());
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.CHALLENGE;
    }
}
