package com.mygdx.game.encounters.fights;

import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.EnemySlots;

import java.util.List;

public abstract class Fight extends Encounter {
    public abstract List<OverthrowActs.ActType> actTypeEncounterable();
    public abstract EnemySlots startingUnits();
}
