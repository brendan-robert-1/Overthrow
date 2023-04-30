package com.mygdx.game.encounters;

import com.mygdx.game.state.items.ItemSlot;

import java.util.Map;

public class Market extends Encounter{
    private Map<ItemSlot, Integer> waresToPrice;
    public Market(Map<ItemSlot, Integer> waresToPrice){
        super(NodeType.MARKET, "Market");
        this.waresToPrice = waresToPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Market");
        return sb.toString();
    }



    public Map<ItemSlot, Integer> getWaresToPrice() {
        return waresToPrice;
    }
}
