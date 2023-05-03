package com.mygdx.game.encounters.fights;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.encounters.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.EnemyCharacter;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.state.EnemySlots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fred extends Fight{
    public Fred(){
        super(NodeType.BOSS_FIGHT, "Boss");
    }
    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS);
    }




    private static final int FRED_STARTING_HP = 20;
    private static final Ability FRED_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability FRED_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability FRED_STARTING_ULT_ABILITY = new Miasma();

    @Override
    public EnemySlots startingUnits() {
        return new EnemySlots(
                generateFred(),
                null,
                null,
                null
        );
    }

    private EnemyCharacter generateFred(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.FRED,
                FRED_STARTING_HP,
                new EquippedGear(),
                FRED_STARTING_BASIC_ABILITY_1,
                FRED_STARTING_BASIC_ABILITY_2,
                FRED_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fred");
        return sb.toString();
    }
}
