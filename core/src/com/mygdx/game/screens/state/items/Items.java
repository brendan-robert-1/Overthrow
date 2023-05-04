package com.mygdx.game.screens.state.items;

import java.util.Map;

public class Items {
    private Map<ItemType, Item> items;



    public Map<ItemType, Item> getItems() {
        return items;
    }



    public Items setItems(Map<ItemType, Item> items) {
        this.items = items;
        return this;
    }
}
