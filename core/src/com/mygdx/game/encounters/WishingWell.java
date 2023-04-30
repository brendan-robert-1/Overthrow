package com.mygdx.game.encounters;

public class WishingWell extends Encounter{
    public WishingWell(){
        super(NodeType.WISHING_WELL, "Wishing Well");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wishing well");
        return sb.toString();
    }
}
