package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.Character;

public class Bloodlet implements Ability {

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
    public boolean selfTargetable() {
        return true;
    }



    @Override
    public void execute(Character target) {
        System.out.println("Executing bloodlust onto: " + target.getName());
    }
}

