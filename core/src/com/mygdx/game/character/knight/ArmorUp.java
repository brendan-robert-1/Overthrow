package com.mygdx.game.character.knight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.ArmorUpBuff;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

public class ArmorUp implements Ability {
    @Override
    public String name() {
        return "Armor Up";
    }



    @Override
    public String description() {
        return "Moderately increases armor for 3 turns";
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
        return true;
    }


    @Override
    public void execute(CharacterPanel target, CharacterPanel source, FightNode fight) {
        System.out.println("Armor up is executing on: " + target.getName());
        target.getBuffsBar().addBuff(new ArmorUpBuff(3));
    }




    @Override
    public AbilityType abilityType() {
        return AbilityType.ARMOR_UP;
    }
}
