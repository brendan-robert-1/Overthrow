package com.mygdx.game.character.enemies.drunkmob;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.encounterscreens.combat.DamageCalculator;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;

public class BottleStab implements Ability {
    private static final int DAMAGE = 5;
    @Override
    public String name() {
        return "Bottle Stab";
    }



    @Override
    public String description() {
        return "Bottle Stab";
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
        int actualDamage = DamageCalculator.calculateDamage(DamageType.PHYSICAL,DAMAGE,source, target);
        target.decreaseHpBy(actualDamage);
    }



    @Override
    public AbilityType abilityType() {
        return null;
    }



    @Override
    public DamageType damageType() {
        return DamageType.PHYSICAL;
    }
}
