package com.mygdx.game.encounters;

import com.mygdx.game.screens.widgets.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class WeaponMerchant extends Encounter{
    private List<InventoryItem> wares = new ArrayList<>();
    public WeaponMerchant(){
        super(NodeType.WEAPON_MERCHANT, "Weapon Merchant");
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Weapon Merchant");
        return sb.toString();
    }
}
