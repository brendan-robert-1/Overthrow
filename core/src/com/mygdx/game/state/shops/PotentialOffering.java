package com.mygdx.game.state.shops;

import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;

public class PotentialOffering {
    private ItemTypeId itemTypeId;
    private int quantityMin;
    private int quantityMax;
    private int priceMin;
    private int priceMax;

    public int getQuantityMin() {
        return quantityMin;
    }



    public PotentialOffering setQuantityMin(int quantityMin) {
        this.quantityMin = quantityMin;
        return this;
    }



    public int getQuantityMax() {
        return quantityMax;
    }



    public PotentialOffering setQuantityMax(int quantityMax) {
        this.quantityMax = quantityMax;
        return this;
    }



    public int getPriceMin() {
        return priceMin;
    }



    public PotentialOffering setPriceMin(int priceMin) {
        this.priceMin = priceMin;
        return this;
    }



    public int getPriceMax() {
        return priceMax;
    }



    public PotentialOffering setPriceMax(int priceMax) {
        this.priceMax = priceMax;
        return this;
    }



    public ItemTypeId getItemTypeId() {
        return itemTypeId;
    }



    public PotentialOffering setItemTypeId(ItemTypeId itemTypeId) {
        this.itemTypeId = itemTypeId;
        return this;
    }
}
