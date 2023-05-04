package com.mygdx.game.state;

import com.mygdx.game.state.items.ItemSlot;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.screens.widgets.InventoryUi.TOTAL_SLOTS;

public class Inventory {
    private final List<ItemSlot> inventoryMap = startingInventory();

    public List<ItemSlot> getInventoryList() {
        return inventoryMap;
    }

    public List<ItemSlot> startingInventory() {
        List<ItemSlot> map =  new ArrayList<>();
        return map;
    }

    public void addItem(ItemSlot itemSlot){
        if(inventoryMap.size() >= TOTAL_SLOTS){
            throw new IllegalStateException("Inventory full debug more.");
        }
        inventoryMap.add(itemSlot);
    }
}
