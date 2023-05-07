package com.mygdx.game.screens.encounterscreens.combat;

import com.mygdx.game.screens.widgets.InventoryItem;

import java.util.List;

public class CombatRewards {
    public int coins;
    public List<InventoryItem> itemRewards;



    public int getCoins() {
        return coins;
    }



    public CombatRewards setCoins(int coins) {
        this.coins = coins;
        return this;
    }



    public List<InventoryItem> getItemRewards() {
        return itemRewards;
    }



    public CombatRewards setItemRewards(List<InventoryItem> itemRewards) {
        this.itemRewards = itemRewards;
        return this;
    }
}
