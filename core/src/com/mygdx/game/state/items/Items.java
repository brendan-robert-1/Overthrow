package com.mygdx.game.state.items;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;

import java.util.Map;

public class Items {
    private Map<ItemTypeId, InventoryItem> items;



    public Map<ItemTypeId, InventoryItem> getItems() {
        return items;
    }



    public Items setItems(Map<ItemTypeId, InventoryItem> items) {
        this.items = items;
        return this;
    }
}
