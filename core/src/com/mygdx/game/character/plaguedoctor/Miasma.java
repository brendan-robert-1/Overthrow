package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;

public class Miasma implements Ability {

    @Override
    public String name() {
        return "Miasma";
    }



    @Override
    public String description() {
        return "Removes 3 turns of debuffs from team.";
    }



    @Override
    public boolean offensiveTargetable() {
        return false;
    }



    @Override
    public boolean selfTargetable() {
        return true;
    }
}

