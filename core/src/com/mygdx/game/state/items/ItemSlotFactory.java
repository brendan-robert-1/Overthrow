package com.mygdx.game.state.items;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.mygdx.game.screens.widgets.InventoryItem;
import com.mygdx.game.state.Inventory;

public class ItemSlotFactory {

    private static final String ITEMS_FILE = "gamedata/items.json";
    private static Items items = loadItems();
    private static ItemSlotFactory instance;
    private ItemSlotFactory(){}
    public static ItemSlotFactory getInstance(){
        if(instance == null){
            instance = new ItemSlotFactory();
        }
        return instance;
    }

    public static InventoryItem of(ItemType itemType) {
        Item item =  items.getItems().get(itemType);
        InventoryItem inventoryItem = new InventoryItem();

    }

    private static Items loadItems(){
        Gson gson = new Gson();
        String jsonStr = Gdx.files.internal(ITEMS_FILE).readString();
        return gson.fromJson(jsonStr, Items.class);
    }

}
