package com.mygdx.game.character.enemies.drunkmob;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;

public class Rob implements Ability {
    @Override
    public String name() {
        return null;
    }



    @Override
    public String description() {
        return null;
    }



    @Override
    public boolean offensiveTargetable() {
        return false;
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

    }



    @Override
    public AbilityType abilityType() {
        return null;
    }



    @Override
    public DamageType damageType() {
        return null;
    }
}
