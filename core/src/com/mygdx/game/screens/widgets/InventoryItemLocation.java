package com.mygdx.game.screens.widgets;
import com.mygdx.game.screens.widgets.InventoryItem.ItemTypeId;

public class InventoryItemLocation {
    private int locationIndex;
    private ItemTypeId itemTypeAtLocation;
    private int numberItemsAtLocation;
    private String itemNameProperty;

    public InventoryItemLocation(){
    }

    public InventoryItemLocation( int locationIndex, ItemTypeId itemTypeAtLocation, int numberItemsAtLocation, String itemNameProperty){
        this.locationIndex = locationIndex;
        this.itemTypeAtLocation = itemTypeAtLocation;
        this.numberItemsAtLocation = numberItemsAtLocation;
        this.itemNameProperty = itemNameProperty;
    }

    public String getItemNameProperty() {
        return itemNameProperty;
    }

    public void setItemNameProperty(String itemNameProperty) {
        this.itemNameProperty = itemNameProperty;
    }

    public ItemTypeId getItemTypeAtLocation() {
        return itemTypeAtLocation;
    }

    public void setItemTypeAtLocation(ItemTypeId itemTypeAtLocation) {
        this.itemTypeAtLocation = itemTypeAtLocation;
    }

    public int getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(int locationIndex) {
        this.locationIndex = locationIndex;
    }

    public int getNumberItemsAtLocation() {
        return numberItemsAtLocation;
    }

    public void setNumberItemsAtLocation(int numberItemsAtLocation) {
        this.numberItemsAtLocation = numberItemsAtLocation;
    }
}