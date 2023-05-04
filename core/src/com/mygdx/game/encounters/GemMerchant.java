package com.mygdx.game.encounters;

import com.mygdx.game.state.items.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class GemMerchant extends Encounter{
    private List<ItemSlot> wares = new ArrayList<>();
    public GemMerchant(){
        super(NodeType.GEM_MERCHANT, "Gem Merchant");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gem Merchant");
        return sb.toString();
    }
}
