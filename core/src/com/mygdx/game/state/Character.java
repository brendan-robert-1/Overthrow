package com.mygdx.game.state;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.knight.KnightGenerator;
import com.mygdx.game.character.plaguedoctor.PlagueDoctorGenerator;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.state.gear.EquippedGear;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Character {

    private String name;
    private CharacterType characterType;
    private int hp;
    private Ability firstBasicAbility;
    private Ability secondBasicAbility;
    private Ability ultimateAbility;
    private final Stats baseStats;
    private Stats inCombatStatModifiers = new Stats();
    private int chargeTime;



    public Character(String name, CharacterType characterType, int hp, Ability firstBasicAbility, Ability secondBasicAbility,
                     Ability ultimateAbility, Stats baseStats, int chargeTime) {
        this.name = name;
        this.characterType = characterType;
        this.hp = hp;
        this.firstBasicAbility = firstBasicAbility;
        this.secondBasicAbility = secondBasicAbility;
        this.ultimateAbility = ultimateAbility;
        this.baseStats = baseStats;
        this.chargeTime = chargeTime;
    }

    public Character(Character character){
        this.name = character.name;
        this.characterType = character.characterType;
        this.hp = character.hp;
        this.firstBasicAbility = character.firstBasicAbility;
        this.secondBasicAbility = character.secondBasicAbility;
        this.ultimateAbility = character.ultimateAbility;
        this.baseStats = character.baseStats;
        this.chargeTime = character.chargeTime;
    }




    public enum CharacterType {
        PLAGUE_DOCTOR, LEPER, INVENTOR, KNIGHT,

        HOMESTEADER_DAD,
        HOMESTEADER_MOM,
        HOMESTEADER_DOG,
        TAX_MAN,
        TAX_COLLECTOR,
        MUTATED_CHICKEN,
        MUTATED_COW,
        MUTATED_PIG,
        FRED, KEYMASTER;
//        public static String toDisplayName(CharacterType characterType){
//            switch(characterType){
//                case PLAGUE_DOCTOR -> {return "Plague Doctor";}
//                case LEPER -> { return "Leper";}
//                case INVENTOR -> {return "Inventor";}
//                case KNIGHT -> {return "Knight";}
//                default -> throw new IllegalStateException("Unexpected value: " + characterType);
//            }
//        }
       
    }

    public static boolean isFriendly(CharacterType characterType){
        AtomicBoolean isFriendly = new AtomicBoolean(false);
        Team.getInstance().streamNonNull().forEach(c -> {
            if(c.getCharacter().getCharacterType() == characterType){
                isFriendly.set(true);
            }
        });
        return isFriendly.get();
    }


    public static Character generateNewCharacter(Character.CharacterType characterType){
        switch(characterType){
            case PLAGUE_DOCTOR -> { return PlagueDoctorGenerator.generateStartingPlagueDoctor(); }
            case LEPER -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            case INVENTOR -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            case KNIGHT -> {return KnightGenerator.generateStartingPlagueDoctor();}
            default -> throw new IllegalStateException("Unexpected value: " + characterType);
        }
    }




    public String getName() {
        return name;
    }



    public Character setName(String name) {
        this.name = name;
        return this;
    }



    public CharacterType getCharacterType() {
        return characterType;
    }



    public Character setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
        return this;
    }



    public int getHp() {
        return hp;
    }



    public Character setHp(int hp) {
        this.hp = hp;
        return this;
    }


    public Ability getFirstBasicAbility() {
        return firstBasicAbility;
    }



    public Character setFirstBasicAbility(Ability firstBasicAbility) {
        this.firstBasicAbility = firstBasicAbility;
        return this;
    }



    public Ability getSecondBasicAbility() {
        return secondBasicAbility;
    }


    public void resetChargeTime(){
        chargeTime = 0;
    }



    public Character setSecondBasicAbility(Ability secondBasicAbility) {
        this.secondBasicAbility = secondBasicAbility;
        return this;
    }



    public Ability getUltimateAbility() {
        return ultimateAbility;
    }



    public Character setUltimateAbility(Ability ultimateAbility) {
        this.ultimateAbility = ultimateAbility;
        return this;
    }

    public Stats getStats(){
        return new Stats(
                baseStats.getArmor() + inCombatStatModifiers.getArmor(),
                baseStats.getMagicResistance() + inCombatStatModifiers.getMagicResistance(),
                baseStats.getPhysicalDamage() + inCombatStatModifiers.getPhysicalDamage(),
                baseStats.getMagicalDamage() + inCombatStatModifiers.getMagicalDamage(),
                baseStats.getSpeed() + inCombatStatModifiers.getSpeed()
        );
    }


    public int getChargeTime() {
        return chargeTime;
    }



    public void reduceCharTimeBy(int reduceBy) {
        int outcome = this.chargeTime - reduceBy;
        if(outcome < 0){outcome = 0;}
        this.chargeTime = outcome;
    }


    public Character setChargeTime(int chargeTime) {
        this.chargeTime = chargeTime;
        return this;
    }

    public Stats getInCombatStatModifiers() {
        return inCombatStatModifiers;
    }

    public void increaseHpBy(int increase){
        this.setHp(hp + increase);
    }

    public Character setInCombatStatModifiers(Stats inCombatStatModifiers) {
        this.inCombatStatModifiers = inCombatStatModifiers;
        return this;
    }

    public void increaseChargeTimeBy(int increaseBy){
        this.chargeTime+=increaseBy;
    }

    public boolean isFriendly(){
         return Team.getInstance().contains(this);
    }


}
