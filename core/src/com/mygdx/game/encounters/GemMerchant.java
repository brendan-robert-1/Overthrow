package com.mygdx.game.encounters;

import com.mygdx.game.screens.widgets.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class GemMerchant extends Encounter{
    private List<InventoryItem> wares = new ArrayList<>();
    public GemMerchant(){
        super(NodeType.GEM_MERCHANT, "Gem Merchant");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Gem Merchant");
        return sb.toString();
    }
}
