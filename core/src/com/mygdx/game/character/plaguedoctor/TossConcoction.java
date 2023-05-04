package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;

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
    public boolean selfTargetable() {
        return false;
    }
}
