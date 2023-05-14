package com.mygdx.game.screens.encounterscreens.combat;

import com.mygdx.game.character.abilities.Ability.DamageType;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.state.Stats;

public class DamageCalculator {

    public static int calculateDamage(DamageType damageType, int baseDamage, CharacterPanel source, CharacterPanel target){
        int finalDamage = baseDamage;
        Stats fullyModifiedSourceStats = source.getFullyModifiedStats();
        Stats fullyModifiedTargetStats = target.getFullyModifiedStats();
        switch(damageType){
            case PHYSICAL -> {
                finalDamage += fullyModifiedSourceStats.getPhysicalDamage();
                finalDamage -= fullyModifiedTargetStats.getArmor();
                if(finalDamage < 0){finalDamage = 0;}
                return finalDamage;
            }
            case MAGICAL -> {
                finalDamage += fullyModifiedSourceStats.getMagicalDamage();
                finalDamage -= fullyModifiedTargetStats.getMagicResistance();
                if(finalDamage < 0){finalDamage = 0;}
                return finalDamage;
            }
            case TRUE -> {
                return finalDamage;
            }
            default -> throw new IllegalStateException("Unexpected value: " + damageType);
        }
    }
}
