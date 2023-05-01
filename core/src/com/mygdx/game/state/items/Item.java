package com.mygdx.game.state.items;

public class Item {
    private ItemType itemType;
    private String displayName;
    private String description;



    public String getDisplayName() {
        return displayName;
    }



    public Item setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }



    public String getDescription() {
        return description;
    }



    public Item setDescription(String description) {
        this.description = description;
        return this;
    }



    public ItemType getItemType() {
        return itemType;
    }



    public Item setItemType(ItemType itemType) {
        this.itemType = itemType;
        return this;
    }
}
