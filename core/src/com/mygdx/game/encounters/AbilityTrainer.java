package com.mygdx.game.encounters;

import com.mygdx.game.character.abilities.Ability;

import java.util.ArrayList;
import java.util.List;

public class AbilityTrainer extends Encounter{
    private List<Ability> abilities = new ArrayList<>();
    public AbilityTrainer(){
        super(NodeType.ABILITY_TRAINER, "Ability Trainer");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbilityTrainer");
        return sb.toString();
    }
}
