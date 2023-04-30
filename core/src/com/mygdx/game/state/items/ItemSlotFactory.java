package com.mygdx.game.state.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.state.shops.MarketOffering;

import java.util.Map;

public class ItemSlotFactory {

    private static final String ITEMS_FILE = "gamedata/items.json";
    private static Items items = loadItems();


    public static ItemSlot one(ItemType itemType){
        Item item =  items.getItems().get(itemType);
        return null;
    }

    private static Items loadItems(){
        Json json = new Json();
        String jsonStr = Gdx.files.internal(ITEMS_FILE).readString();
        return json.fromJson(Items.class, jsonStr);
    }
}
