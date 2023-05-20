package com.mygdx.game.screens.widgets.inventory;

public class ConsumableDetails {
    private boolean isFriendlyTargetable;
    private boolean isFoeTargetable;
    private SplashType splashType;
    private int potency;
    private PotencyType potencyType;
    private PotencyValueType potencyValueType;



    public boolean isFriendlyTargetable() {
        return isFriendlyTargetable;
    }



    public ConsumableDetails setFriendlyTargetable(boolean friendlyTargetable) {
        isFriendlyTargetable = friendlyTargetable;
        return this;
    }



    public boolean isFoeTargetable() {
        return isFoeTargetable;
    }



    public ConsumableDetails setFoeTargetable(boolean foeTargetable) {
        isFoeTargetable = foeTargetable;
        return this;
    }



    public SplashType getSplashType() {
        return splashType;
    }



    public ConsumableDetails setSplashType(SplashType splashType) {
        this.splashType = splashType;
        return this;
    }



    public int getPotency() {
        return potency;
    }



    public ConsumableDetails setPotency(int potency) {
        this.potency = potency;
        return this;
    }



    public PotencyType getPotencyType() {
        return potencyType;
    }



    public ConsumableDetails setPotencyType(PotencyType potencyType) {
        this.potencyType = potencyType;
        return this;
    }



    public PotencyValueType getPotencyValueType() {
        return potencyValueType;
    }



    public ConsumableDetails setPotencyValueType(PotencyValueType potencyValueType) {
        this.potencyValueType = potencyValueType;
        return this;
    }



    public static enum PotencyValueType { FLAT, PERCENT }
    public static enum PotencyType { DAMAGE,HEAL }
    public static enum SplashType { NONE, SURROUNDING, ENTIRE }
}
