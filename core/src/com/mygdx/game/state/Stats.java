package com.mygdx.game.state;

public class Stats {
    public Stats setArmor(int armor) {
        this.armor = armor;
        return this;
    }



    public Stats setMagicResistance(int magicResistance) {
        this.magicResistance = magicResistance;
        return this;
    }



    public Stats setPhysicalDamage(int physicalDamage) {
        this.physicalDamage = physicalDamage;
        return this;
    }



    public Stats setMagicalDamage(int magicalDamage) {
        this.magicalDamage = magicalDamage;
        return this;
    }



    public Stats setSpeed(int speed) {
        this.speed = speed;
        return this;
    }



    private int armor = 0;
    private int magicResistance = 0;
    private int physicalDamage = 0;
    private int magicalDamage = 0;
    private int speed = 0;

    public Stats(int armor, int magicResistance, int physicalDamage, int magicalDamage, int speed){
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.magicalDamage = magicalDamage;
        this.physicalDamage = physicalDamage;
        this.speed = speed;
    }

    public Stats(){

    }

    public void applyModifiers(Stats modifiers){
        if(modifiers == null){modifiers = new Stats();}
        armor += modifiers.getArmor();
        magicResistance += modifiers.getMagicResistance();
        physicalDamage += modifiers.getPhysicalDamage();
        magicalDamage += modifiers.getMagicalDamage();
        speed += modifiers.getSpeed();
    }




    public int getArmor() {
        return armor;
    }



    public int getMagicResistance() {
        return magicResistance;
    }



    public int getPhysicalDamage() {
        return physicalDamage;
    }



    public int getMagicalDamage() {
        return magicalDamage;
    }



    public int getSpeed() {
        return speed;
    }



    public enum StatType {
        ARMOR,
        MAGIC_RESISTANCE,
        PHYSICAL_DAMAGE,
        MAGICAL_DAMAGE,
        SPEED
    }
}
