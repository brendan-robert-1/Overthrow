package com.mygdx.game.state;

import com.mygdx.game.screens.widgets.InventoryItem;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.screens.widgets.InventoryUi.TOTAL_SLOTS;

public class Inventory {
    private final List<InventoryItem> inventoryList = startingInventory();

    public List<InventoryItem> getInventoryList() {
        return inventoryList;
    }

    public List<InventoryItem> startingInventory() {
        List<InventoryItem> map =  new ArrayList<>();
        return map;
    }

    public void addItem(InventoryItem itemSlot){
        if(inventoryList.size() >= TOTAL_SLOTS){
            throw new IllegalStateException("Inventory full debug more.");
        }
        inventoryList.add(itemSlot);
    }
}
