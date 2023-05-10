package com.mygdx.game.character.knight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.buff.PierceBuff;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

import java.util.ArrayList;
import java.util.List;

public class Joust implements Ability {
    @Override
    public String name() {
        return "Joust";
    }



    @Override
    public String description() {
        return "Lunge at targeted enemy dealing moderate damage and applying 2 turns of Pierce";
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
        System.out.println("Joust is executing on: " + target.getName());
        target.decreaseHpBy(50);
        Buff pierce = new PierceBuff(2);
        target.addDebuff(pierce);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.JOUST;
    }
}
