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

public class MutatedLivestock extends Fight{
    public MutatedLivestock(){
        super(NodeType.BOSS_FIGHT, "Boss");
    }
    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS);
    }



    private static final int MUTATED_COW_STARTING_HP = 20;
    private static final Ability MUTATED_COW_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability MUTATED_COW_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability MUTATED_COW_STARTING_ULT_ABILITY = new Miasma();

    private static final int MUTATED_CHICKEN_STARTING_HP = 20;
    private static final Ability MUTATED_CHICKEN_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability MUTATED_CHICKEN_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability MUTATED_CHICKEN_STARTING_ULT_ABILITY = new Miasma();

    private static final int MUTATED_PIG_STARTING_HP = 20;
    private static final Ability MUTATED_PIG_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability MUTATED_PIG_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability MUTATED_PIG_STARTING_ULT_ABILITY = new Miasma();

    @Override
    public EnemySlots startingUnits() {
        return new EnemySlots(
                generateChicken(),
                generatePig(),
                generateCow(),
                null
        );
    }

    private EnemyCharacter generateChicken(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.MUTATED_CHICKEN,
                MUTATED_COW_STARTING_HP,
                new EquippedGear(),
                MUTATED_COW_STARTING_BASIC_ABILITY_1,
                MUTATED_COW_STARTING_BASIC_ABILITY_2,
                MUTATED_COW_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
    private EnemyCharacter generatePig(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.MUTATED_PIG,
                MUTATED_CHICKEN_STARTING_HP,
                new EquippedGear(),
                MUTATED_CHICKEN_STARTING_BASIC_ABILITY_1,
                MUTATED_CHICKEN_STARTING_BASIC_ABILITY_2,
                MUTATED_CHICKEN_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
    private EnemyCharacter generateCow(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.MUTATED_COW,
                MUTATED_PIG_STARTING_HP,
                new EquippedGear(),
                MUTATED_PIG_STARTING_BASIC_ABILITY_1,
                MUTATED_PIG_STARTING_BASIC_ABILITY_2,
                MUTATED_PIG_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MutatedLivestock");
        return sb.toString();
    }
}