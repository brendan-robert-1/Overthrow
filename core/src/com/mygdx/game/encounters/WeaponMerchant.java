package com.mygdx.game.encounters;

import com.mygdx.game.state.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class WeaponMerchant extends Encounter{
    private List<ItemSlot> wares = new ArrayList<>();
    public WeaponMerchant(){
        super(NodeType.WEAPON_MERCHANT);
    }
}
