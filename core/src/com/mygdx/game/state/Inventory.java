package com.mygdx.game.state;

import com.mygdx.game.items.Bandages;
import com.mygdx.game.items.MinorHealthPot;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Integer, ItemSlot> inventoryMap = startingInventory();

    public Map<Integer, ItemSlot> getInventoryMap() {
        return inventoryMap;
    }



    public Map<Integer, ItemSlot> startingInventory() {
        Map<Integer, ItemSlot> map =  new HashMap<>();
        map.put(0, new ItemSlot(MinorHealthPot.name, MinorHealthPot.description, true,  2));
        map.put(1, new ItemSlot(Bandages.name, Bandages.description, true,  1));
        return map;
    }
}
