package com.mygdx.game.state.items;

public class Item {
    private ItemType itemType;
    private String displayName;
    private String description;
    private String spriteName;


    public String getSpriteName() {
        return spriteName;
    }



    public Item setSpriteName(String spriteName) {
        this.spriteName = spriteName;
        return this;
    }







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
