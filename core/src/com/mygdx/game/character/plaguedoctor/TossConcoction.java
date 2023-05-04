package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
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
    public boolean selfTargetable() {
        return false;
    }




    @Override
    public void execute(Character target, Character source) {
        System.out.println(source.getName() + " is executing toss concoction onto: " + target.getName());
        target.decreaseHpBy(15);
    }
}
