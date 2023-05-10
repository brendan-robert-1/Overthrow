package com.mygdx.game.state;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.knight.KnightGenerator;
import com.mygdx.game.character.plaguedoctor.PlagueDoctorGenerator;
import com.mygdx.game.state.gear.EquippedGear;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Character implements Comparable<Character>{

    private String name;
    private CharacterType characterType;
    private int hp;
    private EquippedGear equippedGear;
    private Ability firstBasicAbility;
    private Ability secondBasicAbility;
    private Ability ultimateAbility;
    private List<Buff> buffs = new ArrayList<>();
    private List<Buff> debuffs = new ArrayList<>();
    private final Stats baseStats;
    private Stats inCombatStatModifiers = new Stats();
    private int chargeTime;



    public Character(String name, CharacterType characterType, int hp, EquippedGear equippedGear, Ability firstBasicAbility, Ability secondBasicAbility,
                     Ability ultimateAbility, Stats baseStats, int chargeTime) {
        this.name = name;
        this.characterType = characterType;
        this.hp = hp;
        this.equippedGear = equippedGear;
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
        this.equippedGear = character.equippedGear;
        this.firstBasicAbility = character.firstBasicAbility;
        this.secondBasicAbility = character.secondBasicAbility;
        this.ultimateAbility = character.ultimateAbility;
        this.buffs = character.buffs;
        this.debuffs = character.debuffs;
        this.baseStats = character.baseStats;
        this.chargeTime = character.chargeTime;
    }



    @Override
    public int compareTo(Character o) {
        return Integer.compare(chargeTime, o.chargeTime);
    }



    public void addBuff(Buff buff) {
        if(buffs == null){
            buffs = new ArrayList<>();
        }
        boolean updated = false;
        for(Buff existingBuff : buffs){
            if(existingBuff.buffType == buff.buffType){
                existingBuff.turnsRemaining += buff.turnsRemaining;
                updated = true;
            }
        }
        if(!updated){
            buffs.add(buff);
        }

    }

    public void addDebuff(Buff debuff){
        if(debuffs == null){
            debuffs = new ArrayList<>();
        }
        boolean updated = false;
        for(Buff existingDebuff : debuffs){
            if(existingDebuff.buffType == debuff.buffType){
                existingDebuff.turnsRemaining += debuff.turnsRemaining;
                updated = true;
            }
        }
        if(!updated){
            debuffs.add(debuff);
        }

    }



    public void setBuffs(List<Buff> buffs) {
        this.buffs = buffs;
    }



    public void setDebuffs(List<Buff> debuffs) {
        this.debuffs = debuffs;
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

    public static boolean isFriendly(CharacterType characterType){
        AtomicBoolean isFriendly = new AtomicBoolean(false);
        GameState.getInstance().getCharacterSlots().asList().stream().filter(Objects::nonNull).forEach(c -> {
            if(c.getCharacterType() == characterType){
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

    public List<Buff> getDebuffs() {
        return debuffs;
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



    public List<Buff> getBuffs() {
        return buffs;
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

    public void decreaseHpBy(int decreaseBy){
        this.setHp(hp - decreaseBy);
    }

    public Character setInCombatStatModifiers(Stats inCombatStatModifiers) {
        this.inCombatStatModifiers = inCombatStatModifiers;
        return this;
    }

    public void increaseChargeTimeBy(int increaseBy){
        this.chargeTime+=increaseBy;
    }

    public boolean isFriendly(){
         return GameState.getInstance().getCharacterSlots().contains(this);
    }


}
