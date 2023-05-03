package com.mygdx.game.encounters.state.items;

public class ItemSlot{
    private ItemType itemType;
    private String name;
    private String description;



    public ItemSlot(ItemType itemType, String name, String description, int quantity) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }



    public ItemType getItemType() {
        return itemType;
    }



    public ItemSlot setItemType(ItemType itemType) {
        this.itemType = itemType;
        return this;
    }



    public String getName() {
        return name;
    }



    public ItemSlot setName(String name) {
        this.name = name;
        return this;
    }



    public String getDescription() {
        return description;
    }



    public ItemSlot setDescription(String description) {
        this.description = description;
        return this;
    }



    public int getQuantity() {
        return quantity;
    }



    public ItemSlot setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }



    private int quantity;
}