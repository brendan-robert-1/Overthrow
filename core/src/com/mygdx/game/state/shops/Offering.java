package com.mygdx.game.state.shops;

public class Offering {
    private String itemName;
    private int quantityMin;
    private int quantityMax;
    private int priceMin;
    private int priceMax;



    public String getItemName() {
        return itemName;
    }



    public Offering setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }



    public int getQuantityMin() {
        return quantityMin;
    }



    public Offering setQuantityMin(int quantityMin) {
        this.quantityMin = quantityMin;
        return this;
    }



    public int getQuantityMax() {
        return quantityMax;
    }



    public Offering setQuantityMax(int quantityMax) {
        this.quantityMax = quantityMax;
        return this;
    }



    public int getPriceMin() {
        return priceMin;
    }



    public Offering setPriceMin(int priceMin) {
        this.priceMin = priceMin;
        return this;
    }



    public int getPriceMax() {
        return priceMax;
    }



    public Offering setPriceMax(int priceMax) {
        this.priceMax = priceMax;
        return this;
    }
}
