package com.mygdx.game.encounters.state.items;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;

public class ItemSlotFactory {

    private static final String ITEMS_FILE = "gamedata/items.json";
    private static Items items = loadItems();


    public static ItemSlot one(ItemType itemType){
        Item item =  items.getItems().get(itemType);
        return new ItemSlot(item.getItemType(), item.getDisplayName(), item.getDescription(),1);
    }
    public static ItemSlot of(ItemType itemType, int quantity) {
        Item item =  items.getItems().get(itemType);
        return new ItemSlot(item.getItemType(), item.getDisplayName(), item.getDescription(),quantity);
    }

    private static Items loadItems(){
        Gson gson = new Gson();
        String jsonStr = Gdx.files.internal(ITEMS_FILE).readString();
        return gson.fromJson(jsonStr, Items.class);
    }




}