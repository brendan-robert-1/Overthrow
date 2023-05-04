package com.mygdx.game.state;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.knight.KnightGenerator;
import com.mygdx.game.character.plaguedoctor.PlagueDoctorGenerator;
import com.mygdx.game.state.gear.EquippedGear;

import java.util.List;

public class Character implements Comparable<Character>{

    private String name;
    private CharacterType characterType;
    private int hp;
    private EquippedGear equippedGear;
    private Ability firstBasicAbility;
    private Ability secondBasicAbility;
    private Ability ultimateAbility;
    private List<Buff> buffs;
    private BaseStats baseStats;





    private int chargeTime;



    public Character(String name, CharacterType characterType, int hp, EquippedGear equippedGear, Ability firstBasicAbility, Ability secondBasicAbility,
                     Ability ultimateAbility, List<Buff> buffs, BaseStats baseStats, int chargeTime) {
        this.name = name;
        this.characterType = characterType;
        this.hp = hp;
        this.equippedGear = equippedGear;
        this.firstBasicAbility = firstBasicAbility;
        this.secondBasicAbility = secondBasicAbility;
        this.ultimateAbility = ultimateAbility;
        this.buffs = buffs;
        this.baseStats = baseStats;
        this.chargeTime = chargeTime;
    }



    @Override
    public int compareTo(Character o) {
        return Integer.compare(chargeTime, o.chargeTime);
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
        FRED;
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



    public EquippedGear getEquippedGear() {
        return equippedGear;
    }



    public Character setEquippedGear(EquippedGear equippedGear) {
        this.equippedGear = equippedGear;
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



    public List<Buff> getBuffs() {
        return buffs;
    }



    public Character setBuffs(List<Buff> buffs) {
        this.buffs = buffs;
        return this;
    }



    public BaseStats getBaseStats() {
        return baseStats;
    }



    public Character setBaseStats(BaseStats baseStats) {
        this.baseStats = baseStats;
        return this;
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


}
