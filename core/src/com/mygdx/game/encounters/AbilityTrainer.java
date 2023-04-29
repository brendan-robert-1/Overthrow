package com.mygdx.game.encounters;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class AbilityTrainer extends Encounter{
    private List<Ability> abilities = new ArrayList<>();
    public AbilityTrainer(){
        super(NodeType.ABILITY_TRAINER);
    }
}
