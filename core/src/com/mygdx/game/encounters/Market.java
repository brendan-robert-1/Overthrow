package com.mygdx.game.encounters;

import com.mygdx.game.state.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class Market extends Encounter{
    private List<ItemSlot> wares = new ArrayList<>();
    public Market(){
        super(NodeType.MARKET);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Market");
        return sb.toString();
    }
}
