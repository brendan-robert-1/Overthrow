package com.mygdx.game.screens.widgets.markets;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;

public class MarketOption extends Table {

    private InventoryItem inventoryItem;

    public MarketOption(InventoryItem inventoryItem){
        this.inventoryItem = inventoryItem;
    }

    public String getDisplayName() {
        return inventoryItem.getDisplayName();
    }



    public String getPrice() {
        return String.valueOf(inventoryItem.getCoinValue());
    }



    public String getDisplayText() {
        return inventoryItem.getItemShortDescription();
    }
}
