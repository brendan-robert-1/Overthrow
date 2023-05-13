package com.mygdx.game.encounters;

import com.mygdx.game.character.abilities.Ability;

import java.util.ArrayList;
import java.util.List;

public class AbilityTrainer extends GameNode {
    private List<Ability> abilities = new ArrayList<>();


    @Override
    public String ambientSounds() {
        return null;
    }

    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }



    public AbilityTrainer(){
        super(NodeType.ABILITY_TRAINER, "Ability Trainer");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbilityTrainer");
        return sb.toString();
    }
}
