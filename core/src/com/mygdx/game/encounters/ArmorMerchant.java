package com.mygdx.game.encounters;

import com.mygdx.game.screens.state.items.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class ArmorMerchant extends Encounter{
    private List<ItemSlot> wares = new ArrayList<>();
    public ArmorMerchant(){
        super(NodeType.ARMOR_MERCHANT, "Armor Merchant");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArmorMerchant");
        return sb.toString();
    }
}
