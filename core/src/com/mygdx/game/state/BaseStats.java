package com.mygdx.game.state;

public class BaseStats {

    private int armor;
    private int magicResistance;
    private int physicalDamage;
    private int magicalDamage;
    private int speed;

    public BaseStats(int armor, int magicResistance, int physicalDamage, int magicalDamage, int speed){
        this.armor = armor;
        this.magicResistance = magicResistance;
        this.magicalDamage = magicalDamage;
        this.physicalDamage = physicalDamage;
        this.speed = speed;
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
