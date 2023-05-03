package com.mygdx.game.encounters.state;

import com.mygdx.game.encounters.state.items.ItemSlot;

import java.util.ArrayList;
import java.util.List;

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
        inventoryMap.add(itemSlot);
    }
}
