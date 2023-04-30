package com.mygdx.game.encounters;

import com.mygdx.game.state.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class WishingWell extends Encounter{
    public WishingWell(){
        super(NodeType.WISHING_WELL);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wishing well");
        return sb.toString();
    }
}
