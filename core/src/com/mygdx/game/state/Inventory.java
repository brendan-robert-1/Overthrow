package com.mygdx.game.state;

import com.mygdx.game.items.Bandages;
import com.mygdx.game.items.MinorHealthPot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
