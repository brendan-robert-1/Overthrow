package com.mygdx.game.encounters.fights;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.enemies.homesteaders.DogBite;
import com.mygdx.game.character.enemies.homesteaders.DogGrowl;
import com.mygdx.game.character.enemies.homesteaders.Nourish;
import com.mygdx.game.character.enemies.homesteaders.PitchforkStab;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.Character;

import java.util.Arrays;
import java.util.List;

public class Homesteaders extends FightNode {

    private static final int DOG_STARTING_HP = 7;
    private static final Ability DOG_STARTING_BASIC_ABILITY_1 = new DogBite();
    private static final Ability DOG_STARTING_BASIC_ABILITY_2 = new DogBite();
    private static final Ability DOG_STARTING_ULT_ABILITY = new DogGrowl();

    private static final int MOM_STARTING_HP = 20;
    private static final Ability MOM_STARTING_BASIC_ABILITY_1 = new Nourish();
    private static final Ability MOM_STARTING_BASIC_ABILITY_2 = new Nourish();
    private static final Ability MOM_STARTING_ULT_ABILITY = new Nourish();

    private static final int DAD_STARTING_HP = 23;
    private static final Ability DAD_STARTING_BASIC_ABILITY_1 = new PitchforkStab();
    private static final Ability DAD_STARTING_BASIC_ABILITY_2 = new PitchforkStab();
    private static final Ability DAD_STARTING_ULT_ABILITY = new PitchforkStab();

    @Override
    public String ambientSounds() {
        return null;
    }

    public Homesteaders(){
        super(NodeType.BASIC_FIGHT, "Homesteaders");
    }

    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS, OverthrowActs.ActType.BATTLEFIELD);
    }

    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }

    @Override
    public EnemyTeam startingUnits() {
            return new EnemyTeam(
                    new CharacterPanel(generateHomeSteaderDog()),
                    new CharacterPanel(generateHomeSteaderMom()),
                    new CharacterPanel(generateHomeSteaderDad()),
                    null
            );
        }

    private Character generateHomeSteaderDog(){
        return new Character(
                "Dog",
                Character.CharacterType.HOMESTEADER_DOG,
                DOG_STARTING_HP,
                DOG_STARTING_HP,
                DOG_STARTING_BASIC_ABILITY_1,
                DOG_STARTING_BASIC_ABILITY_2,
                DOG_STARTING_ULT_ABILITY,
                dogBaseStats(),
                0
        );
    }
    private Character generateHomeSteaderMom(){
        return new Character(
                "Homesteader",
                Character.CharacterType.HOMESTEADER_MOM,
                MOM_STARTING_HP,
                MOM_STARTING_HP,
                MOM_STARTING_BASIC_ABILITY_1,
                MOM_STARTING_BASIC_ABILITY_2,
                MOM_STARTING_ULT_ABILITY,
                momBaseStats(),
                0
        );
    }
    private Character generateHomeSteaderDad(){
        return new Character(
                "Homesteader",
                Character.CharacterType.HOMESTEADER_DAD,
                DAD_STARTING_HP,
                DAD_STARTING_HP,
                DAD_STARTING_BASIC_ABILITY_1,
                DAD_STARTING_BASIC_ABILITY_2,
                DAD_STARTING_ULT_ABILITY,
                dogBaseStats(),
                0
        );
    }


    private Stats dogBaseStats(){
        return new Stats(
          0,
          0,
          0,
          0,
          20
        );
    }

    private Stats momBaseStats(){
        return new Stats(
                0,
                0,
                0,
                0,
                14
        );
    }

    private Stats dadBaseStats(){
        return new Stats(
                1,
                0,
                0,
                0,
                13
        );
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HomeSteaders");
        return sb.toString();
    }
}
