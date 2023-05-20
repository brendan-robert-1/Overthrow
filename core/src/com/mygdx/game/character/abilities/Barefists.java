package com.mygdx.game.character.abilities;

import com.mygdx.game.screens.encounterscreens.combat.DamageCalculator;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;

public class Barefists implements Ability{
    private static final int DAMAGE = 3;
    @Override
    public String name() {
        return "Punch";
    }



    @Override
    public String description() {
        return "Punch";
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
        int actualDamage = DamageCalculator.calculateDamage(damageType(), DAMAGE, source, target);
        target.decreaseHpBy(actualDamage);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.FISTS;
    }



    @Override
    public DamageType damageType() {
        return DamageType.PHYSICAL;
    }
}
