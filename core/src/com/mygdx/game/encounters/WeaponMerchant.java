package com.mygdx.game.encounters;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;

import java.util.ArrayList;
import java.util.List;

public class WeaponMerchant extends GameNode {

    @Override
    public String ambientSounds() {
        return null;
    }
    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }
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
