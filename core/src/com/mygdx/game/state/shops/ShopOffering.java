package com.mygdx.game.state.shops;

import com.mygdx.game.screens.widgets.InventoryItem;

public class ShopOffering {
    private InventoryItem inventoryItem;
    private int price;



    public int getPrice() {
        return price;
    }



    public ShopOffering setPrice(int price) {
        this.price = price;
        return this;
    }



    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }



    public ShopOffering setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
        return this;
    }
}
