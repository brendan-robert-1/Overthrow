package com.mygdx.game.state.items;

public class ItemSlot{
    private ItemType itemType;
    private String name;
    private String description;
    private String spriteName;
    private int quantity;


    public ItemSlot(ItemType itemType, String name, String description, int quantity, String spriteName) {
        this.itemType = itemType;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.spriteName = spriteName;
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



    public String getSpriteName() {
        return spriteName;
    }



    public ItemSlot setSpriteName(String spriteName) {
        this.spriteName = spriteName;
        return this;
    }
}