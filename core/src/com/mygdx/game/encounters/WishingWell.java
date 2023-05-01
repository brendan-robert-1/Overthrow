package com.mygdx.game.encounters;

import java.util.Random;

public class WishingWell extends Encounter{

    private int percentChance = 50;
    public WishingWell(){
        super(NodeType.WISHING_WELL, "Wishing Well");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wishing well");
        return sb.toString();
    }

    public void throwCoins(){
        percentChance += 10;
    }

    public int getPercentChance(){
        return percentChance;
    }

    public boolean wishSuccessful(){
        return new Random().nextInt(0, 100) < percentChance;
    }
}
