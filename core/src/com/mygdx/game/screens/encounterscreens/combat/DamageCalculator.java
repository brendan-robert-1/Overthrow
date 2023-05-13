package com.mygdx.game.screens.encounterscreens.combat;

import com.mygdx.game.character.abilities.Ability.DamageType;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;

public class DamageCalculator {

    public static int calculateDamage(DamageType damageType, int baseDamage, CharacterPanel source, CharacterPanel target){
        int finalDamage = baseDamage;
        switch(damageType){
            case PHYSICAL -> {
                finalDamage += source.getCharacter().getStats().getPhysicalDamage();
                finalDamage -= target.getCharacter().getStats().getArmor();
                if(finalDamage < 0){finalDamage = 0;}
                return finalDamage;
            }
            case MAGICAL -> {
                finalDamage += source.getCharacter().getStats().getMagicalDamage();
                finalDamage -= target.getCharacter().getStats().getMagicResistance();
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
