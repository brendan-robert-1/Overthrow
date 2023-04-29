package com.mygdx.game.encounters.fights;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.encounters.EnemyCharacter;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.EnemySlots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homesteaders extends Fight {

    private static final int DOG_STARTING_HP = 7;
    private static final Ability DOG_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability DOG_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability DOG_STARTING_ULT_ABILITY = new Miasma();

    private static final int MOM_STARTING_HP = 20;
    private static final Ability MOM_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability MOM_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability MOM_STARTING_ULT_ABILITY = new Miasma();

    private static final int DAD_STARTING_HP = 23;
    private static final Ability DAD_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability DAD_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability DAD_STARTING_ULT_ABILITY = new Miasma();


    public Homesteaders(){
        super(NodeType.FIGHT);
    }

    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS, OverthrowActs.ActType.BATTLEFIELD);
    }

    @Override
    public EnemySlots startingUnits() {
            return new EnemySlots(
                    generateHomeSteaderDog(),
                    generateHomeSteaderMom(),
                    generateHomeSteaderDad(),
                    null
            );
        }

    private EnemyCharacter generateHomeSteaderDog(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.HOMESTEADER_DOG,
                DOG_STARTING_HP,
                new EquippedGear(),
                DOG_STARTING_BASIC_ABILITY_1,
                DOG_STARTING_BASIC_ABILITY_2,
                DOG_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
    private EnemyCharacter generateHomeSteaderMom(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.HOMESTEADER_MOM,
                MOM_STARTING_HP,
                new EquippedGear(),
                MOM_STARTING_BASIC_ABILITY_1,
                MOM_STARTING_BASIC_ABILITY_2,
                MOM_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
    private EnemyCharacter generateHomeSteaderDad(){
        return new EnemyCharacter(
                EnemyCharacter.EnemyType.HOMESTEADER_DAD,
                DAD_STARTING_HP,
                new EquippedGear(),
                DAD_STARTING_BASIC_ABILITY_1,
                DAD_STARTING_BASIC_ABILITY_2,
                DAD_STARTING_ULT_ABILITY,
                new ArrayList<>()
        );
    }
}
