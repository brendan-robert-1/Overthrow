package com.mygdx.game.encounters.fights;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.Character;
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
        super(NodeType.BASIC_FIGHT, "Fight");
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

    private Character generateHomeSteaderDog(){
        return new Character(
                "Dog",
                Character.CharacterType.HOMESTEADER_DOG,
                DOG_STARTING_HP,
                new EquippedGear(),
                DOG_STARTING_BASIC_ABILITY_1,
                DOG_STARTING_BASIC_ABILITY_2,
                DOG_STARTING_ULT_ABILITY,
                new ArrayList<>(),
                dogBaseStats(),
                0
        );
    }
    private Character generateHomeSteaderMom(){
        return new Character(
                "Homesteader",
                Character.CharacterType.HOMESTEADER_MOM,
                MOM_STARTING_HP,
                new EquippedGear(),
                MOM_STARTING_BASIC_ABILITY_1,
                MOM_STARTING_BASIC_ABILITY_2,
                MOM_STARTING_ULT_ABILITY,
                new ArrayList<>(),
                momBaseStats(),
                0
        );
    }
    private Character generateHomeSteaderDad(){
        return new Character(
                "Homesteader",
                Character.CharacterType.HOMESTEADER_DAD,
                DAD_STARTING_HP,
                new EquippedGear(),
                DAD_STARTING_BASIC_ABILITY_1,
                DAD_STARTING_BASIC_ABILITY_2,
                DAD_STARTING_ULT_ABILITY,
                new ArrayList<>(),
                dogBaseStats(),
                0
        );
    }


    private Stats dogBaseStats(){
        return new Stats(
          20,
          20,
          20,
          20,
          20
        );
    }

    private Stats momBaseStats(){
        return new Stats(
                20,
                20,
                20,
                20,
                20
        );
    }

    private Stats dadBaseStats(){
        return new Stats(
                20,
                20,
                20,
                20,
                20
        );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HomeSteaders");
        return sb.toString();
    }
}
