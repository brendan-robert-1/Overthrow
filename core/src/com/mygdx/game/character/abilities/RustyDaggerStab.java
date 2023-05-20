package com.mygdx.game.character.abilities;

import com.mygdx.game.screens.encounterscreens.combat.DamageCalculator;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;

public class RustyDaggerStab implements Ability{
    private static final int DAMAGE = 4;



    @Override
    public String name() {
        return "Stab";
    }



    @Override
    public String description() {
        return DAMAGE + " physical damage to a single target.";
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
        if(target.getCharacter().isFriendly()){

        }else {
            EnemyTeam enemyTeam = fight.getEnemyTeam();
        }
        target.decreaseHpBy(actualDamage);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.RUSTY_DAGGER;
    }



    @Override
    public DamageType damageType() {
        return DamageType.PHYSICAL;
    }
}
