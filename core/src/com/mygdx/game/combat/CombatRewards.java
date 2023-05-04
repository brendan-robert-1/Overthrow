package com.mygdx.game.combat;

import com.mygdx.game.screens.state.items.ItemSlot;

import java.util.List;

public class CombatRewards {
    public int coins;
    public List<ItemSlot> itemRewards;



    public int getCoins() {
        return coins;
    }



    public CombatRewards setCoins(int coins) {
        this.coins = coins;
        return this;
    }



    public List<ItemSlot> getItemRewards() {
        return itemRewards;
    }



    public CombatRewards setItemRewards(List<ItemSlot> itemRewards) {
        this.itemRewards = itemRewards;
        return this;
    }
}
