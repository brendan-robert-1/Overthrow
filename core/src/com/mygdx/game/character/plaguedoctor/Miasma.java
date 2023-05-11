package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

import java.util.ArrayList;
import java.util.Objects;

public class Miasma implements Ability {

    @Override
    public String name() {
        return "Miasma";
    }



    @Override
    public String description() {
        return "Removes 3 turns of debuffs from team.";
    }



    @Override
    public boolean offensiveTargetable() {
        return false;
    }



    @Override
    public boolean friendlyTargetable() {
        return true;
    }



    @Override
    public boolean selfTargetable() {
        return true;
    }

    @Override
    public void execute(CharacterPanel target, CharacterPanel source, FightNode fight){
        System.out.println("Miasma is executing on: " + target.getName());
        Team.getInstance().streamNonNull()
                .forEach(character ->  {
                    //character.setDebuffs(new ArrayList<>());
                });
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.MIASMA;
    }
}

