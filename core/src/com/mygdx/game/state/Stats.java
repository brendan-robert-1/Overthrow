package com.mygdx.game.state;

public class Stats {

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
