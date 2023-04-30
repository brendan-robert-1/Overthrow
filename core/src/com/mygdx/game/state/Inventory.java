package com.mygdx.game.state;

import com.mygdx.game.state.items.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<ItemSlot> inventoryMap = startingInventory();

    public List<ItemSlot> getInventoryMap() {
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
