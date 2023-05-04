package com.mygdx.game.state.shops;

import com.mygdx.game.state.items.ItemSlot;

public class ShopOffering {
    private ItemSlot itemSlot;
    private int price;



    public int getPrice() {
        return price;
    }



    public ShopOffering setPrice(int price) {
        this.price = price;
        return this;
    }



    public ItemSlot getItemSlot() {
        return itemSlot;
    }



    public ShopOffering setItemSlot(ItemSlot itemSlot) {
        this.itemSlot = itemSlot;
        return this;
    }
}
